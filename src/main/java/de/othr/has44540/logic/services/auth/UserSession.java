package de.othr.has44540.logic.services.auth;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionDTO;
import de.othr.has44540.persistance.entities.user.AbstractUser;

public class UserSession {

    private AbstractUser user;

    private AbstractUser onBehalfOf;

    private SessionDTO oAuthSession;

    public UserSession(AbstractUser user, SessionDTO oAuthSession) {
        this.user = user;
        this.oAuthSession = oAuthSession;
    }

    public UserSession(AbstractUser user, AbstractUser onBehalfOf, SessionDTO oAuthSession) {
        this(user, oAuthSession);
        this.onBehalfOf = onBehalfOf;
    }

    public AbstractUser getUser() {
        return user;
    }

    public AbstractUser getOnBehalfOf() {
        return onBehalfOf;
    }

    /**
     * Returns the logged in user.
     * If the logged in user acts on behalf of another user,
     * this user will be returned.
     *
     * @return the user which authorises queries
     */
    public AbstractUser getExecutiveUser() {
        return onBehalfOf != null ? onBehalfOf : user;
    }

    public SessionDTO getoAuthSession() {
        return oAuthSession;
    }

    public void setoAuthSession(SessionDTO oAuthSession) {
        this.oAuthSession = oAuthSession;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    public void setOnBehalfOf(AbstractUser onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }
}
