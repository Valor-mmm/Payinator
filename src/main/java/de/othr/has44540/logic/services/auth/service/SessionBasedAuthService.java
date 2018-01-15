package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionLinkDTO;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.token.IllegalTokenChangeException;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.SessionScoped;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@SessionScoped
public class SessionBasedAuthService extends AbstractAuthService {

    private static final Logger logger = Logger.getLogger(SessionBasedAuthService.class.getName());

    private UserSession session;
    private AuthToken authToken;

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
    public AuthToken loginSimpleUser(@NotNull String email, @NotNull String password) throws
                                                                            InvalidLoginDataException,
                                                                            InternalErrorException,
                                                                            OAuthException,
                                                                            AuthException {
        logger.info("Logging in user with email [" + email + "]");
        UserSession newSession = super.initOAuthSession(email, password);
        logger.info("Login successful for email [" + email + "]");
        return saveUserSession(newSession);
    }

    @Override
    public AuthToken linkCompany(SessionLinkDTO sessionLink) throws
                                                             AuthException,
                                                             OAuthException,
                                                             InternalErrorException {
        logger.info("Receiving session link from [" + sessionLink.getFromSiteId() + "]");
        UserSession newSession = super.initOAuthSession(sessionLink);
        logger.info("Linked session successfully for site [" + sessionLink.getFromSiteId() + "]");
        return saveUserSession(newSession);
    }

    @Override
    public AuthToken logout(AuthToken authToken) {
        this.session = null;
        this.authToken = null;
        return authToken;
    }

    @Override
    public void setAuthToken(AuthToken token) throws InvalidTokenException {
        try {
            if (this.authToken.checkToken(token))
                this.authToken = token;
        } catch (IllegalTokenChangeException e) {
            throw new InvalidTokenException(token);
        }
    }

    public void setSession(UserSession session) {
        this.session = session;
    }

    private AuthToken saveUserSession(UserSession newSession) {
        this.session = newSession;
        AuthToken token = new AuthToken();
        this.authToken = token;
        return authToken;
    }
}

