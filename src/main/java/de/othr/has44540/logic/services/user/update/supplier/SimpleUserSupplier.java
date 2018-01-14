package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleUserSupplier implements Supplier<SimpleUser> {

    private static final Logger logger = Logger.getLogger(SimpleUserSupplier.class.getName());

    @PersistenceContext
    private EntityManager em;

    private Long oAuthId;
    private Email email;

    private Supplier<SimpleAccount> simpleAccountSupplier;
    private Supplier<DonorAccount> donorAccountSupplier;

    public SimpleUserSupplier(Long oAuthID, Email email) {
        this.oAuthId = oAuthID;
        this.email = email;
        donorAccountSupplier = new DonorAccountSupplier();
    }

    @Override
    @Transactional
    public SimpleUser get() {
        SimpleUser simpleUser = new SimpleUser();

        DonorAccount donorAccount = donorAccountSupplier.get();

        simpleAccountSupplier = new SimpleAccountSupplier(donorAccount);
        Set<SimpleAccount> simpleAccounts = new TreeSet<>();
        simpleAccounts.add(simpleAccountSupplier.get());
        simpleUser.setSimpleAccounts(simpleAccounts);

        simpleUser.setDonorAccount(donorAccount);

        email = handleEmail(email);
        simpleUser.setEmail(email);

        simpleUser.setOauthId(oAuthId);
        return simpleUser;
    }

    @Transactional
    private Email handleEmail(Email email) {
        TypedQuery<Email> emailQ = em.createNamedQuery("emailByLocalPartAndDomain", Email.class);
        emailQ.setParameter("localPart", email.getLocalPart());
        emailQ.setParameter("domain", email.getDomain());
        Email result;
        try {
            result = emailQ.getSingleResult();
            return result;
        } catch (NoResultException ex) {
            logger.log(Level.CONFIG, "No email with this local part and domain exists yet.", ex);
        }

        em.persist(email);
        result = email;
        return result;
    }
}
