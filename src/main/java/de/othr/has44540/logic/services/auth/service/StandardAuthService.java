package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

public class StandardAuthService implements AuthServiceIF {

    private static final Logger logger = Logger.getLogger(StandardAuthService.class.getName());

    private UserSession session;

    @PersistenceContext
    private EntityManager em;

    @Override
    public AbstractUser getLoggedInUser() {
        return session.getUser();
    }

    @Override
    public AbstractUser getOnBehalfOf() {
        return session.getOnBehalfOf();
    }

    @Override
    public AbstractUser getExecutiveUser() {
        return session.getOnBehalfOf();
    }

    @Override
    @Transactional
    public AuthToken login(String email, String password) {
        //TypedQuery<Email> emailQuerry = em.createQuery("SELECT e FROM Email AS e WHERE Email.localPart+'@'+Email" +
        //                                                        ".domain = :email", Email.class);
        return null;
    }

    @Override
    public AuthToken login() {
        return null;
    }

    @Override
    public void setAuthToken(AuthToken token) {

    }

    public void setSession(UserSession session) {
        this.session = session;
    }
}
