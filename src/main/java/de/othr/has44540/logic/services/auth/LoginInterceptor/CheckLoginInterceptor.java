package de.othr.has44540.logic.services.auth.LoginInterceptor;

import de.othr.has44540.logic.services.auth.service.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.NotLoggedInException;
import de.othr.has44540.logic.services.auth.service.AuthServiceQualifier;
import de.othr.has44540.logic.services.auth.token.AuthToken;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CheckLogin
public class CheckLoginInterceptor {

    private AuthServiceIF authServiceIF;

    @AroundInvoke
    public Object checkLogin(InvocationContext context) throws Exception {

        AuthServiceCase serviceCase = determineAuthService(context.getParameters());
        if (serviceCase.equals(AuthServiceCase.TOKEN_BASED)) {
            submitAuthToken(context.getParameters());
        }

        if (authServiceIF.getLoggedInUser() == null) {
            throw new NotLoggedInException();
        }

        Object result = context.proceed();
        return result;
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

        authServiceIF.setAuthToken((AuthToken) parameters[0]);
    }

    private AuthServiceCase determineAuthService(Object[] parameters) {
        if (parameters == null) {
            return AuthServiceCase.STANDARD;
        }
        if (parameters.length <= 0 || parameters[0] == null) {
            return AuthServiceCase.STANDARD;
        }
        if (parameters[0] instanceof AuthToken) {
            return AuthServiceCase.TOKEN_BASED;
        }
        return AuthServiceCase.STANDARD;
    }

}
