package de.othr.has44540.logic.services.exceptions.auth;

public class InvalidLoginDataException extends AuthException {

    private String email;

    public static final String errMessage = "The email and password you entered did not match.";

    public InvalidLoginDataException() {

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
}
