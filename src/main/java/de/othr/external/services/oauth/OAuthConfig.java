package de.othr.external.services.oauth;

import org.jetbrains.annotations.NotNull;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class OAuthConfig implements Serializable {

    private String oauthCustomerToken;
    private String oauthHost;

    private static final Logger logger = Logger.getLogger(OAuthConfig.class.getName());

    @PostConstruct
    public void initializeConfig() {
        Properties properties = getProperties();
        oauthCustomerToken = properties.getProperty("apiToken");
        oauthHost = properties.getProperty("host");
        if (oauthCustomerToken == null) {
            logger.severe("No customer api token for oauthService found.");
        }
        if (oauthHost == null) {
            logger.warning("No host for oauth specified. Using default host of wsdl.");
        }
        logger.info("Successfully loaded oauth config file.");
    }

    private static @NotNull Properties getProperties() {
        ClassLoader classLoader = OAuthConfig.class.getClassLoader();
        InputStream propertyStream = classLoader.getResourceAsStream("oAuthConfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(propertyStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not load properties file for oauth.", e);
        }
        return properties;
    }

    public String getOauthCustomerToken() {
        return oauthCustomerToken;
    }

    public void setOauthCustomerToken(String oauthCustomerToken) {
        this.oauthCustomerToken = oauthCustomerToken;
    }

    public String getOauthHost() {
        return oauthHost;
    }

    public void setOauthHost(String oauthHost) {
        this.oauthHost = oauthHost;
    }
}
