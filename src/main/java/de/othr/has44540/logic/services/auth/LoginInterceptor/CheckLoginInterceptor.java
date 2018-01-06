package de.othr.has44540.logic.services.auth.LoginInterceptor;

import de.othr.has44540.logic.services.auth.AuthServiceIF;
import de.othr.has44540.logic.services.auth.NotLoggedInException;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CheckLogin
public class CheckLoginInterceptor {

    @Inject
    private AuthServiceIF authServiceIF;

    @AroundInvoke
    public Object checkLogin(InvocationContext context) throws Exception {

        if (authServiceIF.getLoggedInUser() == null) {
            throw new NotLoggedInException();
        }

        Object result = context.proceed();
        return result;
    }
}
