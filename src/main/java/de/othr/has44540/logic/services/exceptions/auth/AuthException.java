package de.othr.has44540.logic.services.exceptions.auth;

import de.othr.has44540.logic.services.exceptions.AppException;

public class AuthException extends AppException {

    public AuthException() {

    }

    public AuthException(String title, String message){
        super(title, message);
    }

}
