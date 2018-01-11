package de.othr.has44540.logic.services.exceptions;

public class InternalErrorException extends AppException {

    public InternalErrorException() {

    }

    public InternalErrorException(String title, String description) {
        super(title, description);
    }
}
