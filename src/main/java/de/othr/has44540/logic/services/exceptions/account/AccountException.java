package de.othr.has44540.logic.services.exceptions.account;

import de.othr.has44540.logic.services.exceptions.AppException;

public class AccountException extends AppException {

    public AccountException(String title, String message) {
        super(title, message);
    }

    public AccountException() {

    }
 }
