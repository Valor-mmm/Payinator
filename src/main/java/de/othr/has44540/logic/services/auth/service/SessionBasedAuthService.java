package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@SessionScoped
public class SessionBasedAuthService implements AuthServiceIF {

    private static final Logger logger = Logger.getLogger(SessionBasedAuthService.class.getName());

    private UserSession session;
    private AuthToken authToken;

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
    public AuthToken login(@NotNull String email, @NotNull String password) throws InvalidLoginDataException {
        Email foundEmail = AuthServiceCommons.queryEmail(email, em);
        if (foundEmail == null) {
            // TODO: email not found
        }

        AbstractUser user;
        user = foundEmail != null ? foundEmail.getUser() : null;
        if (user == null) {
            // TODO: better err handling
            return null;
        }

        // TODO: korbis part

        UserSession newSession = new UserSession(user);
        this.session = newSession;
        AuthToken token = new AuthToken();
        this.authToken = token;
        return authToken;
    }

    @Override
    public AuthToken login() throws InvalidLinkObjectException {
        // TODO: implement method
        return null;
    }

    @Override
    public void setAuthToken(AuthToken token) throws InvalidTokenException {
        this.authToken = token;
    }

    public void setSession(UserSession session) {
        this.session = session;
    }
}
