package de.othr.has44540.logic.services.auth.service;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.user.AbstractUser;

public class TokenBasedAuthService implements AuthServiceIF {

    @Override
    public AbstractUser getLoggedInUser() {
        return null;
    }

    @Override
    public AbstractUser getOnBehalfOf() {
        return null;
    }

    @Override
    public AbstractUser getExecutiveUser() {
        return null;
    }

    @Override
    public AuthToken login(String email, String password) {
        return null;
    }

    @Override
    public AuthToken login() {
        return null;
    }

    @Override
    public void setAuthToken(AuthToken token) {

    }
}
