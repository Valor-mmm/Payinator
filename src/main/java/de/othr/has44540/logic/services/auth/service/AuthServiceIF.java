package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLinkObjectException;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import java.io.Serializable;

public interface AuthServiceIF extends Serializable{

    AbstractUser getLoggedInUser();

    AbstractUser getOnBehalfOf();

    AbstractUser getExecutiveUser();

    AuthToken login(String email, String password) throws InvalidLoginDataException;

    // TODO korbis link object
    AuthToken login() throws InvalidLinkObjectException;

    void setAuthToken(AuthToken token) throws InvalidTokenException;

}
