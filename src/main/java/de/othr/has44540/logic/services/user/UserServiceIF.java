package de.othr.has44540.logic.services.user;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import java.io.Serializable;

public interface UserServiceIF extends Serializable {

    AbstractUser deleteUser(AuthToken authToken) throws AuthException;
}
