package de.othr.external.services.oauth;

import de.othr.external.services.oauth.korbinianSchmidt.data.DataService;
import de.othr.external.services.oauth.korbinianSchmidt.data.DataServiceService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomDataServiceService extends DataServiceService implements Serializable {

    private static final Logger logger = Logger.getLogger(CustomSessionServiceService.class.getName());

    @Inject
    OAuthConfig oAuthConfig;

    private ChangeServiceHost<DataService> changeServiceHost;

    public CustomDataServiceService() {
        changeServiceHost = new ChangeServiceHost<>();
    }

    public DataService getDataService() {
        DataService service = super.getDataServicePort();
        if (oAuthConfig.getOauthHost() == null || service == null) {
            logger.info("No host specified or service is null. Using standard service");
            return service;
        }
        String newHost = oAuthConfig.getOauthHost() + "DataService";
        logger.info("Changing host of service to: " + newHost);
        return changeServiceHost.changeServiceHost(service, newHost);
    }

}
