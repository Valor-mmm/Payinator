package de.othr.has44540.logic.services.exceptions.auth;

import de.othr.has44540.logic.services.auth.token.AuthToken;

public class InvalidTokenException extends AuthException {

    private AuthToken authToken;

    public InvalidTokenException() {
        super("The given token is not valid.", "The given token could be expired and removed, illegally changed or " +
                "never been valid.");
    }

    public InvalidTokenException(AuthToken token) {
        this();
        authToken = token;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

}
