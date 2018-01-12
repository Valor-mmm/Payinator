package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.UserSession;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.SessionScoped;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@SessionScoped
public class SessionBasedAuthService extends AbstractAuthService {

    private static final Logger logger = Logger.getLogger(SessionBasedAuthService.class.getName());

    private UserSession session;
    private AuthToken authToken;

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
        UserSession newSession = super.initOAuthSession(email, password);
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
}
