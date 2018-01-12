package de.othr.external.services.oauth;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionService;
import de.othr.external.services.oauth.korbinianSchmidt.session.SessionServiceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomSessionServiceService extends SessionServiceService implements Serializable {

    private static final Logger logger = Logger.getLogger(CustomSessionServiceService.class.getName());

    @Inject
    OAuthConfig oAuthConfig;

    private ChangeServiceHost<SessionService> changeServiceHost;

    public CustomSessionServiceService() {
        changeServiceHost = new ChangeServiceHost<>();
    }

    public SessionService getSessionService() {
        SessionService service = super.getSessionServicePort();
        if (oAuthConfig.getOauthHost() == null || service == null) {
            logger.info("No host specified or service is null. Using standard service");
            return service;
        }
        String newHost = oAuthConfig.getOauthHost() + "SessionService";
        logger.info("Changing host of service to: " + newHost);
        return changeServiceHost.changeServiceHost(service, newHost);
    }
}
