package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionLinkDTO;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.token.AuthTokenManager;
import de.othr.has44540.logic.services.auth.token.IllegalTokenChangeException;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
public class TokenBasedAuthService extends AbstractAuthService {

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
    public AuthToken login(@NotNull String email, @NotNull String password) throws
                                                                            InvalidLoginDataException,
                                                                            InternalErrorException {
        UserSession session = super.initOAuthSession(email, password);
        AuthToken token = new AuthToken();
        tokenManager.addToken(token, session);
        return token;
    }

    @Override
    public AuthToken login(SessionLinkDTO sessionLink) throws InvalidLinkObjectException {
        logger.info("Receiving session link from [" + sessionLink.getFromSiteId() + "]");
        UserSession newSession = super.initOAuthSession(sessionLink);
        AuthToken token = new AuthToken();
        tokenManager.addToken(token, newSession);
        logger.info("Linked session successfully for site [" + sessionLink.getFromSiteId() + "]");
        return token;
    }

    @Override
    public void setAuthToken(AuthToken token) throws InvalidTokenException {
        boolean validToken = false;
        try {
            validToken = tokenManager.checkToken(token);
        } catch (IllegalTokenChangeException e) {
            logger.log(Level.SEVERE, "Token was illegally changed", e);
        }
        
        if (!validToken) {
            throw new InvalidTokenException(token);
        }
        this.authToken = token;
    }

    public AuthTokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(AuthTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Nullable
    private UserSession getSession() {
        try {
            return tokenManager.getSessionForToken(authToken);
        } catch (IllegalTokenChangeException e) {
            logger.log(Level.SEVERE, "Token was changed illegally.", e);
            return null;
        }
    }
}
