package de.othr.has44540.logic.services.exceptions;

public enum OAuthCause {

    UNKNOWN_ERROR("Unexpected Error", "Unexpected error during oauth service communication."),
    SITE_TOKEN_INVALID("An internal error occurred.",
            "An internal error occurred during communication with oauth service. The given site token was invalid"),
    DATA_SERVICE_ERROR(
            "Error from oAuth data service.", "Data service responded with an error"),
    INTERNAL_ERROR("The Oauth Service is currently not available.", "OAuth Service responds with internal server error.");

    private String title;
    private String message;

    OAuthCause(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
