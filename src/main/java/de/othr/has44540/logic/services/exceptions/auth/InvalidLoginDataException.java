package de.othr.has44540.logic.services.exceptions.auth;

public class InvalidLoginDataException extends AuthException {

    private String email;

    private boolean invalidEmail = false;
    private boolean invalidPassword = false;

    public static final String errMessage = "The email and password you entered did not match.";

    public InvalidLoginDataException() {

    }

    public InvalidLoginDataException(String title, String message) {
        super(title, message);
    }

    public InvalidLoginDataException(String title, String message, boolean invalidEmail, boolean invalidPassword) {
        this(title, message);
        this.invalidEmail = invalidEmail;
        this.invalidPassword = invalidPassword;
    }

    public InvalidLoginDataException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInvalidEmail() {
        return invalidEmail;
    }

    public void setInvalidEmail(boolean invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public boolean isInvalidPassword() {
        return invalidPassword;
    }

    public void setInvalidPassword(boolean invalidPassword) {
        this.invalidPassword = invalidPassword;
    }
}
