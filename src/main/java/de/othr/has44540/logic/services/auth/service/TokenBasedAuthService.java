package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionLinkDTO;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.token.AuthTokenManager;
import de.othr.has44540.logic.services.auth.token.IllegalTokenChangeException;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService
@SessionScoped
public class TokenBasedAuthService extends AbstractAuthService {

    private static final Logger logger = Logger.getLogger(TokenBasedAuthService.class.getName());

    @EJB
    private AuthTokenManager tokenManager;

    private AuthToken authToken;

    @Override
    @WebMethod(exclude = true)
    public AbstractUser getLoggedInUser() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getUser();
    }

    @Override
    @WebMethod(exclude = true)
    public AbstractUser getOnBehalfOf() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getOnBehalfOf();
    }

    @Override
    @WebMethod(exclude = true)
    public AbstractUser getExecutiveUser() {
        UserSession session = getSession();
        if (session == null) {
            return null;
        }
        return session.getExecutiveUser();
    }

    @Override
    public AuthToken loginSimpleUser(@NotNull String email, @NotNull String password) throws
                                                                            InvalidLoginDataException,
                                                                            InternalErrorException,
                                                                            OAuthException,
                                                                            AuthException {
        logger.info("Logging in: [" + email + "]");
        UserSession session = super.initOAuthSession(email, password);
        AuthToken token = new AuthToken();
        tokenManager.addToken(token, session);
        logger.info("Successfully logged in: [" + email + "] with token id: [" + token.getId() + "]");
        return token;
    }

    @Override
    public AuthToken linkCompany(SessionLinkDTO sessionLink) throws InvalidLinkObjectException {
        logger.info("Receiving session link from [" + sessionLink.getFromSiteId() + "]");
        UserSession newSession = super.initOAuthSession(sessionLink);
        AuthToken token = new AuthToken();
        tokenManager.addToken(token, newSession);
        logger.info("Linked session successfully for site [" + sessionLink.getFromSiteId() + "]");
        return token;
    }

    @Override
    public AuthToken logout(AuthToken authToken) throws InvalidTokenException {
        if (!this.authToken.equals(authToken)) {
            throw new InvalidTokenException(authToken);
        }
        try {
            this.tokenManager.removeToken(authToken);
        } catch (IllegalTokenChangeException e) {
            throw new InvalidTokenException(authToken);
        }
        this.authToken = null;
        return authToken;
    }

    @Override
    @WebMethod(exclude = true)
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

    @WebMethod(exclude = true)
    public AuthTokenManager getTokenManager() {
        return tokenManager;
    }

    @WebMethod(exclude = true)
    public void setTokenManager(AuthTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Nullable
    @WebMethod(exclude = true)
    private UserSession getSession() {
        try {
            return tokenManager.getSessionForToken(authToken);
        } catch (IllegalTokenChangeException e) {
            logger.log(Level.SEVERE, "Token was changed illegally.", e);
            return null;
        }
    }
}
