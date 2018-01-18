package de.othr.has44540.logic.services.user.update;

import de.othr.external.services.oauth.CustomDataServiceService;
import de.othr.external.services.oauth.korbinianSchmidt.data.*;
import de.othr.external.services.oauth.korbinianSchmidt.session.SessionDTO;
import de.othr.has44540.logic.seeder.user.CompanySeeder;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthCause;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.user.update.supplier.EntitySupplierCase;
import de.othr.has44540.logic.services.user.update.supplier.EntitySupplierQualifier;
import de.othr.has44540.logic.services.user.update.supplier.SimpleUserSupplier;
import de.othr.has44540.logic.services.user.update.updater.EntityUpdaterIF;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterCase;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterQalifier;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import de.othr.has44540.persistance.entities.user.roles.Company;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateServiceImpl implements UpdateServiceIF {

    private static final Logger logger = Logger.getLogger(UpdateServiceImpl.class.getName());

    private DataService dataService;

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.SIMPLE_USER)
    private Supplier<SimpleUser> simpleUserSupplier;

    @PersistenceContext
    private EntityManager em;

    @Inject
    @EntityUpdaterQalifier(EntityUpdaterCase.PERSONAL_INFO)
    private EntityUpdaterIF<PersonalDataDTO, PersonalInformation> persInfoUpdater;

    @Inject
    @EntityUpdaterQalifier(EntityUpdaterCase.PAYMENT_INFORMATION)
    private EntityUpdaterIF<List<PaymentMethod>, Set<AbstractPaymentMethod>> paymentMethodUpdater;

    @EJB
    private CompanySeeder companySeeder;

    @PostConstruct
    @Inject
    public void initUpdateService(CustomDataServiceService dataServiceService) {
        dataService = dataServiceService.getDataService();
    }

    @Override
    @Transactional
    public SimpleUser updateUser(@NotNull SessionDTO oAuthSession, SimpleUser user, Email email) throws
                                                                                                 OAuthException,
                                                                                                 InternalErrorException {

        de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO dataSession = convertSession(oAuthSession);
        PersonalDataDTO personalDataDTO = getPersonalData(dataSession);
        List<PaymentMethod> paymentMethods = getPaymentMethods(dataSession);

        PersonalInformation oldPersonalInformation = user != null ? user.getPersonalInformation() : null;
        PersonalInformation updatedInformation = persInfoUpdater.update(personalDataDTO, oldPersonalInformation);

        Set<AbstractPaymentMethod> currentSet = user != null ? user.getPaymentMethods() : null;
        Set<AbstractPaymentMethod> updatedPaymentMethods = paymentMethodUpdater.update(paymentMethods, currentSet);


        if (user == null) {
            logger.info("User was null. Trying to get user with same oauth ID");
            user = getUserByOauthId(oAuthSession.getUserId());
            logger.info("User found: " + (user != null));
        }
        if (user == null) {
            initSimpleUserSupplier(oAuthSession.getUserId());
            user = simpleUserSupplier.get();
            user.setPersonalInformation(updatedInformation);
            user.setPaymentMethods(updatedPaymentMethods);
            user.setUsername(oAuthSession.getUsername());
        } else {
            user.setPersonalInformation(updatedInformation);
            user.setPaymentMethods(updatedPaymentMethods);
            user = em.merge(user);
        }
        if (email != null) {
            email = findEmail(email);
            email.setUser(user);
            // dont need to persist. will be persisted with user; (Cascade)
        }
        user.setEmail(email);
        em.persist(user);
        return user;
    }

    private @NotNull PersonalDataDTO getPersonalData(
            de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO session) throws OAuthException {
        PersonalDataDTO personalDataDTO;
        try {
            personalDataDTO = dataService.getPersonalData(session);
        } catch (DataServiceException_Exception e) {
            DataServiceException exception = e.getFaultInfo();
            if (exception == null) {
                throw new OAuthException("Unknown OAuth Exception", "Fault info of exception was not accessible",
                                         OAuthCause.UNKNOWN_ERROR);
            }
            throw new OAuthException("OAuth Data Service Error: ", exception.getMessage(),
                                     OAuthCause.DATA_SERVICE_ERROR);
        }
        if (personalDataDTO == null) {
            throw new OAuthException("OAuth Error", "Data Service returned PersonalData which was null",
                                     OAuthCause.UNKNOWN_ERROR);
        }
        return personalDataDTO;
    }

    private List<PaymentMethod> getPaymentMethods(
            de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO session) throws OAuthException {
        List<PaymentMethod> paymentMethods;
        try {
            paymentMethods = dataService.getPaymentMethods(session);
        } catch (DataServiceException_Exception e) {
            DataServiceException exception = e.getFaultInfo();
            if (exception == null) {
                throw new OAuthException("Unknown OAuth Exception", "Fault info of exception was not accessible",
                                         OAuthCause.UNKNOWN_ERROR);
            }
            throw new OAuthException("OAuth Data Service Error: ", exception.getMessage(),
                                     OAuthCause.DATA_SERVICE_ERROR);
        }

        if (paymentMethods == null) {
            paymentMethods = new ArrayList<>();
        }

        return paymentMethods;
    }

    private void initSimpleUserSupplier(Long oAuthID) {
        if (simpleUserSupplier instanceof SimpleUserSupplier) {
            SimpleUserSupplier implementation = (SimpleUserSupplier) simpleUserSupplier;
            implementation.setoAuthId(oAuthID);
            simpleUserSupplier = implementation;
        }
    }

    @Override
    public Company updateCompany(@NotNull Long externalSiteId) {
        companySeeder.seedCompany();

        TypedQuery<Company> companyQuery = em
                .createQuery("SELECT c FROM Company AS c WHERE c.externalSiteId = :siteId", Company.class);
        companyQuery.setParameter("siteId", externalSiteId);
        try {
            return companyQuery.getSingleResult();
        } catch (NoResultException ex) {
            logger.log(Level.SEVERE, "No company found. Seeding Error is possible.", ex);
        }
        return null;
    }

    private Email findEmail(@NotNull Email email) throws InternalErrorException {
        TypedQuery<Email> emailQ = em.createNamedQuery("emailByLocalPartAndDomain", Email.class);
        emailQ.setParameter("localPart", email.getLocalPart());
        emailQ.setParameter("domain", email.getDomain());
        try {
            return emailQ.getSingleResult();
        } catch (NoResultException ex) {
            logger.log(Level.INFO, "Could not find email of already persisted user.", ex);
            return email;
        }
    }

    private de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO convertSession(
            @NotNull SessionDTO session) {
        de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO dataSession = new de.othr.external.services.oauth.korbinianSchmidt.data.SessionDTO();
        dataSession.setCreationDate(session.getCreationDate());
        dataSession.setExpirationDate(session.getExpirationDate());
        dataSession.setSessionToken(session.getSessionToken());
        dataSession.setSiteId(session.getSiteId());
        dataSession.setUserId(session.getUserId());
        dataSession.setUsername(session.getUsername());
        return dataSession;
    }

    private SimpleUser getUserByOauthId(Long id) {
        TypedQuery<SimpleUser> userByOAuthIdQuery = em
                .createQuery("SELECT u FROM SimpleUser AS u WHERE u.oauthId = :oAuthID", SimpleUser.class)
                .setParameter("oAuthID", id);
        try {
            return userByOAuthIdQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
