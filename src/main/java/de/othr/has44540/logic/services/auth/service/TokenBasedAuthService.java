package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.token.AuthTokenManager;
import de.othr.has44540.logic.services.auth.token.IllegalTokenChangeException;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
public class TokenBasedAuthService implements AuthServiceIF {

    private static final Logger logger = Logger.getLogger(TokenBasedAuthService.class.getName());

    @EJB
    private AuthTokenManager tokenManager;

    private AuthToken authToken;

    @Override
    public AbstractUser getLoggedInUser() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getUser();
    }

    @Override
    public AbstractUser getOnBehalfOf() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getOnBehalfOf();
    }

    @Override
    public AbstractUser getExecutiveUser() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getExecutiveUser();
    }

    @Override
    public AuthToken login(String email, String password) {
        // TODO: when standard is finished
        return null;
    }

    @Override
    public AuthToken login() {
        // TODO: when standard is finished;
        return null;
    }

    @Override
    public void setAuthToken(AuthToken token) {
        this.authToken = token;
    }

    public AuthTokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(AuthTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    private UserSession getSession() {
        try {
            return tokenManager.getSessionForToken(authToken);
        } catch (IllegalTokenChangeException e) {
            logger.log(Level.SEVERE, "Token was changed illegally.", e);
            return null;
        }
    }
}
