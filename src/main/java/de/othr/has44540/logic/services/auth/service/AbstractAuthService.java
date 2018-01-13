package de.othr.has44540.logic.services.auth.service;

import de.othr.external.services.oauth.CustomSessionServiceService;
import de.othr.external.services.oauth.OAuthConfig;
import de.othr.external.services.oauth.korbinianSchmidt.session.*;
import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthCause;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.user.update.UpdateServiceIF;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractAuthService implements AuthServiceIF {

    private static final Logger logger = Logger.getLogger(AbstractAuthService.class.getName());

    @Inject
    private AuthServiceCommons commons;

    @Inject
    private OAuthConfig oAuthConfig;

    @Inject
    private UpdateServiceIF updateService;

    private SessionService sessionService;


    @PostConstruct
    @Inject
    public void initializeSessionService(CustomSessionServiceService sessionServiceService) {
        sessionService = sessionServiceService.getSessionService();
    }


    UserSession initOAuthSession(@NotNull String email, @NotNull String password) throws
                                                                                  InvalidLoginDataException,
                                                                                  InternalErrorException,
                                                                                  OAuthException {
        AbstractUser foundUser = commons.getUser(email);

        LoginDataDTO loginData = new LoginDataDTO();
        loginData.setEmail(email);
        loginData.setPassword(password);

        SessionDTO session = sendLoginRequest(loginData);

        AbstractUser updateResult = updateService.updateUser(session.getSessionToken(), foundUser);
        if (updateResult == null) {
            throw new InternalErrorException("Could not update user.",
                                             "The user could neither be updated nor be created");
        }

        return new UserSession(updateResult, session);
    }

    UserSession initOAuthSession(@NotNull SessionLinkDTO sessionLink) throws
                                                                      InvalidLinkObjectException,
                                                                      InternalServerErrorException {
        return new UserSession(null, null);
    }

    private SessionDTO sendLoginRequest(LoginDataDTO loginData) throws InvalidLoginDataException, OAuthException {
        SessionDTO oAuthSession = null;
        try {
            oAuthSession = sessionService.create(oAuthConfig.getOauthCustomerToken(), loginData);
        } catch (SessionServiceException_Exception e) {
            SessionServiceException serviceException = e.getFaultInfo();
            switch (serviceException.getReason()) {
                case NO_SUCH_SITE:
                    logger.log(Level.SEVERE, "Site token invalid", serviceException);
                    throw new OAuthException(OAuthCause.SITE_TOKEN_INVALID);
                case INTERNAL_FAILURE:
                    logger.log(Level.SEVERE, "OAuth Service responds with internal server error.", serviceException);
                    throw new OAuthException(OAuthCause.INTERNAL_ERROR);
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

        return oAuthSession;
    }


    private void throwUnknownOauthException(Object exception) throws OAuthException {
        logger.log(Level.SEVERE, "Login failed during unexpected error with oAuth communication", exception);
        throw new OAuthException(OAuthCause.UNKNOWN_ERROR);
    }
}
