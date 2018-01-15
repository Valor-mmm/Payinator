package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AuthServiceCommons implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthServiceCommons.class.getName());

    @PersistenceContext
    private EntityManager em;

    @NotNull
    protected static String[] checkEmail(String email) throws InvalidLoginDataException {
        String localPart = null;
        String domain = null;
        try {
            localPart = Email.getLocalPart(email);
            domain = Email.getDomain(email);
        } catch (IllegalArgumentException e) {
            throw new InvalidLoginDataException("Invalid email", "The email you entered did not match an email format.",
                                                true, false);
        }
        return new String[]{localPart, domain};
    }

    protected Email queryEmail(@NotNull String email) throws InvalidLoginDataException {
        String[] emailParts = checkEmail(email);
        String localPart = emailParts[0];
        String domain = emailParts[1];

        TypedQuery<Email> emailQuery = em.createNamedQuery("emailByLocalPartAndDomain", Email.class);
        emailQuery.setParameter("localPart", localPart);
        emailQuery.setParameter("domain", domain);
        Email result = null;
        try {
            result = emailQuery.getSingleResult();
        } catch (NoResultException ex) {
            logger.log(Level.INFO, "No email for this criteria found", ex);
        }
        return result;
    }

    protected AbstractUser getUser(@NotNull String email) throws InvalidLoginDataException {
        Email foundEmail = queryEmail(email);

        if (foundEmail == null) {
            return null;
        }
        return foundEmail.getUser();
    }

    protected AbstractUser findUser(Long oauthId) {
        TypedQuery<AbstractUser> userQ = em
                .createQuery("SELECT u FROM AbstractUser AS u WHERE u.oauthId = :oauthId", AbstractUser.class);
        userQ.setParameter("oauthId", oauthId);
        try {
            return userQ.getSingleResult();
        } catch (NoResultException noFound) {
            logger.log(Level.CONFIG, "No user with oaut id [" + oauthId + "] found.", noFound);
        }
        return null;
    }
}
