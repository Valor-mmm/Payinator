package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.korbinianSchmidt.OAuthConfig;
import de.othr.external.services.oauth.korbinianSchmidt.session.*;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.InternalServerErrorException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@SessionScoped
public class SessionBasedAuthService implements AuthServiceIF {

    private static final Logger logger = Logger.getLogger(SessionBasedAuthService.class.getName());

    private UserSession session;
    private AuthToken authToken;

    /**
     * External service
     */
    private SessionService sessionService;
    @Inject
    private OAuthConfig oauthConfig;

    @PersistenceContext
    private EntityManager em;

    public SessionBasedAuthService() {
        sessionService = new SessionServiceService().getSessionServicePort();
    }

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

        AbstractUser user = null;
        if (foundEmail != null) {
            user = foundEmail.getUser();
        }

        LoginDataDTO loginDataDTO = new LoginDataDTO();
        loginDataDTO.setEmail(email);
        loginDataDTO.setPassword(password);
        SessionDTO oAuthSession = null;
        try {
            oAuthSession = sessionService.create(oauthConfig.getOauthCustomerToken(), loginDataDTO);
        } catch (SessionServiceException_Exception e) {
            SessionServiceException serviceException = e.getFaultInfo();
            switch (serviceException.getReason()) {
                case NO_SUCH_SITE:
                    logger.log(Level.SEVERE, "Site token invalid", serviceException);
                    throw new InternalServerErrorException("Site token is invalid.");
            }
        }

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
