package de.othr.has44540.logic.services.exceptions;

public class OAuthException extends AppException {

    private OAuthCause errorReason;

    public OAuthException(OAuthCause errorReason) {
        this.errorReason = errorReason;
    }

    public OAuthException(String title, String message, OAuthCause errorReason) {
        super(title, message);
        this.errorReason = errorReason;
    }

    public OAuthCause getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(OAuthCause errorReason) {
        this.errorReason = errorReason;
    }
}
