package de.othr.has44540.logic.services.exceptions.auth;

import de.othr.external.services.oauth.korbinianSchmidt.session.SessionLinkDTO;

public class InvalidLinkObjectException extends AuthException {

    private SessionLinkDTO sessionLink;

    public InvalidLinkObjectException(String title, String description) {
        super(title, description);
    }

    public InvalidLinkObjectException(String title, String description, SessionLinkDTO sessionLink) {
        this(title, description);
        this.sessionLink = sessionLink;
    }

    public SessionLinkDTO getSessionLink() {
        return sessionLink;
    }

    public void setSessionLink(SessionLinkDTO sessionLink) {
        this.sessionLink = sessionLink;
    }
}
