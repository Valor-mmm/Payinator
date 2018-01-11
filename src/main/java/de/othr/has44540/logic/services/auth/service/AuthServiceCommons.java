package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AuthServiceCommons {

    public static Email queryEmail(@NotNull String email, @NotNull EntityManager em) {
        String localPart = null;
        String domain = null;
        try {
            localPart = Email.getLocalPart(email);
            domain = Email.getDomain(email);
        } catch (IllegalArgumentException e) {
            throw new InvalidLoginDataException("Invalid email", "The email you entered did not match an email format.",
                                                true, false);
        }

        TypedQuery<Email> emailQuery = em
                .createQuery("SELECT e FROM Email AS e WHERE e.localPart = :localPart AND e.domain = :domain",
                             Email.class);
        emailQuery.setParameter("localPart", localPart);
        emailQuery.setParameter("domain", domain);
        return emailQuery.getSingleResult();
    }
}
