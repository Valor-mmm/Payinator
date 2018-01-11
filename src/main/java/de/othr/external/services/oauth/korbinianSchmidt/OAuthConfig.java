package de.othr.external.services.oauth.korbinianSchmidt;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class OAuthConfig implements Serializable{

    private String oauthCustomerToken;
    private static final Logger logger = Logger.getLogger(OAuthConfig.class.getName());

    public OAuthConfig() {
        ClassLoader classLoader = OAuthConfig.class.getClassLoader();
        InputStream propertyStream = classLoader.getResourceAsStream("oAuthConfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(propertyStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not load properties file for oauth.", e);
        }
        oauthCustomerToken = properties.getProperty("apiToken");
        if (oauthCustomerToken == null) {
            logger.severe("No customer api token for oauthService found.");
        }
        logger.info("Successfully loaded oauth config file.");
    }

    public String getOauthCustomerToken() {
        return oauthCustomerToken;
    }

    public void setOauthCustomerToken(String oauthCustomerToken) {
        this.oauthCustomerToken = oauthCustomerToken;
    }
}
