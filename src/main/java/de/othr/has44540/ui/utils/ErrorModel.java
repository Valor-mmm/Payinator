package de.othr.has44540.ui.utils;

import de.othr.has44540.ui.IndexModel;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class ErrorModel implements Serializable {

    private static final Logger logger = Logger.getLogger(ErrorModel.class.getName());

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

    public static void redirectToErrorPage() {
        logger.info("Redirecting to error page.");
        IndexModel.redirectToPage(pageName);
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
