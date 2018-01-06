package de.othr.has44540.logic.services.auth;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;

public interface AuthServiceIF {

    AbstractUser getLoggedInUser();

    AuthToken login(Email email, String password);

    // TODO korbis link object
    AuthToken login();

}
