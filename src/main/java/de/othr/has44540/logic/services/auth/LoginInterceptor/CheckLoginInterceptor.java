package de.othr.has44540.logic.services.auth.LoginInterceptor;

import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.InvalidLoginException;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifierImpl;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import org.jetbrains.annotations.Contract;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Interceptor
@CheckLogin
public class CheckLoginInterceptor implements Serializable{

    private static final Logger logger = Logger.getLogger(CheckLoginInterceptor.class.getName());

    @Inject
    @Any
    private Instance<AuthServiceIF> authServices;

    private AuthServiceIF authService;

    @AroundInvoke
    public Object checkLogin(InvocationContext context) throws Exception {

        AuthServiceCase serviceCase = determineAuthService(context.getParameters());

        // Setting the auth service dynamically
        setAuthService(serviceCase);

        if (serviceCase.equals(AuthServiceCase.TOKEN_BASED)) {
            submitAuthToken(context.getParameters());
        }

        if (authService.getLoggedInUser() == null) {
            throw new InvalidLoginException();
        }

        return context.proceed();
    }

    private void submitAuthToken(Object[] parameters) {
        if (parameters == null) {
            return;
        }

        if (parameters.length <= 0 || parameters[0] == null) {
            return;
        }
        if (!(parameters[0] instanceof AuthToken)) {
            return;
        }

        authService.setAuthToken((AuthToken) parameters[0]);
    }

    @Contract(pure = true)
    private AuthServiceCase determineAuthService(Object[] parameters) {
        if (parameters == null) {
            return AuthServiceCase.SESSION_BASED;
        }
        if (parameters.length <= 0 || parameters[0] == null) {
            return AuthServiceCase.SESSION_BASED;
        }
        if (parameters[0] instanceof AuthToken) {
            return AuthServiceCase.TOKEN_BASED;
        }
        return AuthServiceCase.SESSION_BASED;
    }

    /**
     * Method to set the auth service property dynamically by the given serviceCase
     * @param serviceCase The service case to select the authService implementation
     * @throws UnsatisfiedResolutionException if could not select the desired authService
     */
    private void setAuthService(AuthServiceCase serviceCase) {
        AuthServiceQualifierImpl authServiceQualifier = new AuthServiceQualifierImpl(serviceCase);
        try {
            authService = authServices.select(authServiceQualifier).get();
        } catch (UnsatisfiedResolutionException e) {
            logger.log(Level.SEVERE, "Could not determine AuthService", e);
            throw e;
        }
    }

}
