package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import java.io.Serializable;

public interface AuthServiceIF extends Serializable{

    AbstractUser getLoggedInUser();

    AbstractUser getOnBehalfOf();

    AbstractUser getExecutiveUser();

    AuthToken login(String email, String password);

    // TODO korbis link object
    AuthToken login();

    void setAuthToken(AuthToken token);

}
