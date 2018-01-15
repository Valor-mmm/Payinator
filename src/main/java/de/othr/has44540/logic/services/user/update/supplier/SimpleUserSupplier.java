package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@EntitySupplierQualifier(EntitySupplierCase.SIMPLE_USER)
public class SimpleUserSupplier implements Supplier<SimpleUser>, Serializable {

    private static final Logger logger = Logger.getLogger(SimpleUserSupplier.class.getName());

    @PersistenceContext
    private EntityManager em;

    private Long oAuthId;
    private Email email;

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.SIMPLE_ACCOUNT)
    private Supplier<SimpleAccount> simpleAccountSupplier;

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.DONOR_ACCOUNT)
    private Supplier<DonorAccount> donorAccountSupplier;

    @Override
    @Transactional
    public SimpleUser get() {
        SimpleUser simpleUser = new SimpleUser();

        DonorAccount donorAccount = donorAccountSupplier.get();

        initSimpleAccountSupplier(donorAccount);
        Set<SimpleAccount> simpleAccounts = new TreeSet<>();
        simpleAccounts.add(simpleAccountSupplier.get());
        simpleUser.setSimpleAccounts(simpleAccounts);

        simpleUser.setDonorAccount(donorAccount);

        if (email != null) {
            email = handleEmail(email);
            simpleUser.setEmail(email);
        }

        simpleUser.setOauthId(oAuthId);
        return simpleUser;
    }

    @Transactional
    private Email handleEmail(Email email) {
        TypedQuery<Email> emailQ = em.createNamedQuery("emailByLocalPartAndDomain", Email.class);
        emailQ.setParameter("localPart", email.getLocalPart());
        emailQ.setParameter("domain", email.getDomain());
        Email result = null;
        try {
            result = emailQ.getSingleResult();
            return result;
        } catch (NoResultException ex) {
            logger.log(Level.CONFIG, "No email with this local part and domain exists yet.", ex);
        }
        if (result == null) {
            em.persist(email);
            result = email;
        }
        return result;
    }

    private void initSimpleAccountSupplier(DonorAccount donorAccount) {
        if (simpleAccountSupplier instanceof SimpleAccountSupplier) {
            ((SimpleAccountSupplier) simpleAccountSupplier).setDonorAccount(donorAccount);
        }
    }

    public Long getoAuthId() {
        return oAuthId;
    }

    public void setoAuthId(Long oAuthId) {
        this.oAuthId = oAuthId;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
