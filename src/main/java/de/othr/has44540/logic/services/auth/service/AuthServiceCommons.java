package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthServiceCommons {

    private static final Logger logger = Logger.getLogger(AuthServiceCommons.class.getName());

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

    public static Email queryEmail(@NotNull String email, @NotNull EntityManager em) throws InvalidLoginDataException {
        String[] emailParts = checkEmail(email);
        String localPart = emailParts[0];
        String domain = emailParts[1];

        TypedQuery<Email> emailQuery = em
                .createQuery("SELECT e FROM Email AS e WHERE e.localPart = :localPart AND e.domain = :domain",
                             Email.class);
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
}
