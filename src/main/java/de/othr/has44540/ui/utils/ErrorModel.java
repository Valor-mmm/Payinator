package de.othr.has44540.ui.utils;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ErrorModel implements Serializable {

    public static final String pageName = "error";

    private String title;
    private String message;

    public void setError(String title, String message) {
        setTitle(title);
        setMessage(message);
    }

    public String getTitle() {
        return title;
    }

    public void setUnknownError() {
        setTitle("An unexpected error happened.");
        setMessage("Ups. This should not have happened. Im sure it will work next time. Please try again.");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
