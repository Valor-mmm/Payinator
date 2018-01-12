package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.CustomSessionServiceService;
import de.othr.external.services.oauth.OAuthConfig;
import de.othr.external.services.oauth.korbinianSchmidt.session.*;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.user.update.UpdateServiceIF;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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

    @Inject
    private UpdateServiceIF updateService;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    @Inject
    public void initializeSessionService(CustomSessionServiceService sessionServiceService) {
        sessionService = sessionServiceService.getSessionService();
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
    public AuthToken login(@NotNull String email, @NotNull String password) throws
                                                                            AuthException,
                                                                            InternalErrorException {
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
                    throw new InternalErrorException("An internal error occurred.",
                                                     "An internal error occurred during communication with oauth service.");
                case INTERNAL_FAILURE:
                    logger.log(Level.SEVERE, "OAuth Service responds with internal server error.", serviceException);
                    throw new InternalErrorException("The Oauth Service is currently not available.",
                                                     "Because of an error in the OAuthServicePlease try again later.");
                case INVALID_LOGIN_DATA:
                    logger.log(Level.SEVERE, "Login data invalid.", serviceException);
                    throw new InvalidLoginDataException();
                default:
                    throwUnknownOauthException(serviceException);
            }
        } catch (Exception exception) {
            throwUnknownOauthException(exception);
        }

        if (oAuthSession == null) {
            throwUnknownOauthException(new NullPointerException("Returned session is null."));
        }
        if (oAuthSession.getSessionToken() == null) {
            throwUnknownOauthException(new NullPointerException("Returned sessionToken is null."));
        }

        updateService.updateUser(oAuthSession.getSessionToken(), user);

        UserSession newSession = new UserSession(user, oAuthSession);
        this.session = newSession;
        AuthToken token = new AuthToken();
        this.authToken = token;
        return authToken;
    }

    @Override
    public AuthToken login() throws AuthException {
        // TODO: implement method
        return null;
    }

    @Override
    public void setAuthToken(AuthToken token) throws AuthException {
        this.authToken = token;
    }

    public void setSession(UserSession session) {
        this.session = session;
    }

    private void throwUnknownOauthException(Object exception) throws InternalErrorException {
        logger.log(Level.SEVERE, "Login failed during unexpected oauth exception", exception);
        throw new InternalErrorException("Unexpected Error.", "Unexpected error during oauth service communication.");
    }
}
