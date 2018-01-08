package de.othr.has44540.logic.services.auth;

import de.othr.has44540.persistance.entities.user.AbstractUser;

public class UserSession {

    private AbstractUser user;

    private AbstractUser onBehalfOf;

    public UserSession(AbstractUser user) {
        this.user = user;
    }

    public UserSession(AbstractUser user, AbstractUser onBehalfOf) {
        this(user);
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
}
