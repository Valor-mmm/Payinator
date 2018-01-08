package de.othr.has44540.logic.services.auth.service.factory;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.StandardAuthService;
import de.othr.has44540.logic.services.auth.service.TokenBasedAuthService;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

@SessionScoped
public class AuthServiceFactory implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthServiceFactory.class.getName());

    @Inject
    private AuthServiceMemory serviceMemory;

    @Produces
    @AuthServiceQualifier(AuthServiceCase.STANDARD)
    @SessionScoped
    public AuthServiceIF getStandardService(StandardAuthService standardAuthService) {
        serviceMemory.setServiceCase(AuthServiceCase.STANDARD);
        return standardAuthService;
    }

    @Produces
    @SessionScoped
    @AuthServiceQualifier(AuthServiceCase.TOKEN_BASED)
    public AuthServiceIF getTokenBasedService(TokenBasedAuthService tokenBasedAuthService) {
        serviceMemory.setServiceCase(AuthServiceCase.TOKEN_BASED);
        return tokenBasedAuthService;
    }

    @Produces
    @DetectAutomatically
    public AuthServiceIF getServiceAutomatically(StandardAuthService standardAuthService,
                                                 TokenBasedAuthService tokenBasedAuthService) {
        if (serviceMemory.getServiceCase() == null) {
            logger.config("Assuming session management is not available, creating token based service.");
            return getTokenBasedService(tokenBasedAuthService);
        }

        switch (serviceMemory.getServiceCase()) {
            case STANDARD:
                return getStandardService(standardAuthService);
            case TOKEN_BASED:
                return getTokenBasedService(tokenBasedAuthService);
            default:
                String errMessage = "No case for AuthServiceCase " + serviceMemory.getServiceCase();
                logger.severe(errMessage);
                throw new UnsupportedOperationException(errMessage);
        }
    }
}
