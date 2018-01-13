package de.othr.has44540.logic.services.user.update;

import de.othr.external.services.oauth.CustomDataServiceService;
import de.othr.external.services.oauth.korbinianSchmidt.data.*;
import de.othr.has44540.logic.services.exceptions.OAuthCause;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.user.update.updater.PersInfoUpdater;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import de.othr.has44540.persistance.entities.user.roles.Company;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UpdateServiceImpl implements UpdateServiceIF {

    private static final Logger logger = Logger.getLogger(UpdateServiceImpl.class.getName());

    private DataService dataService;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersInfoUpdater persInfoUpdater;

    @PostConstruct
    @Inject
    public void initUpdateService(CustomDataServiceService dataServiceService) {
        dataService = dataServiceService.getDataService();
    }

    @Override
    @Transactional
    public SimpleUser updateUser(@NotNull String sessionToken, SimpleUser user) throws OAuthException {

        PersonalDataDTO personalDataDTO = getPersonalData(sessionToken);
        List<PaymentMethod> paymentMethods = getPaymentMethods(sessionToken);

        PersonalInformation oldPersonalInformation = user != null ? user.getPersonalInformation() : null;
        PersonalInformation updatedInformation = persInfoUpdater
                .update(personalDataDTO, oldPersonalInformation);



    }

    private @NotNull PersonalDataDTO getPersonalData(String sessionToken) throws OAuthException {
        PersonalDataDTO personalDataDTO;
        try {
            personalDataDTO = dataService.getPersonalData(sessionToken);
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

    private List<PaymentMethod> getPaymentMethods(String sessionToken) throws OAuthException {
        List<PaymentMethod> paymentMethods;
        try {
            paymentMethods = dataService.getPaymentMethods(sessionToken);
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

    @Override
    public Company updateCompany(@NotNull Long externalSiteId) {
        return null;
    }
}
