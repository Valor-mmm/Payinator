package de.othr.has44540.logic.services.exceptions.auth;

import de.othr.has44540.logic.services.auth.token.AuthToken;

public class InvalidTokenException extends AuthException {

    private AuthToken authToken;

    public InvalidTokenException() {

    }

    public InvalidTokenException(AuthToken token) {
        authToken = token;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

}
