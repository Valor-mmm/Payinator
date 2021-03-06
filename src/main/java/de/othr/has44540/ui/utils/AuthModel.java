package de.othr.has44540.ui.utils;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifier;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.logic.services.exceptions.auth.InvalidTokenException;
import de.othr.has44540.ui.IndexModel;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class AuthModel implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthModel.class.getName());

    public static final String pageName = "login";

    private boolean loggedIn = false;

    @Inject
    @AuthServiceQualifier(AuthServiceCase.SESSION_BASED)
    private AuthServiceIF authService;

    @Inject
    private ErrorModel errorModel;

    private String email;

    private String password;

    private String prevPage;

    private String message;

    public void requireLogin(String prevPage) {
        this.prevPage = prevPage;
        this.message = "The current site requires a login. Please log in to access the site.";
    }

    public String login() {
        if (email == null || password == null || email.length() < 3 || password.length() < 3) {
            String errTitle = "Email or password is empty.";
            logger.warning(errTitle);
            errorModel.setError(errTitle, "The email or password you entered were empty. Please fill in both fields");
            return ErrorModel.pageName;
        }

        try {
            loggedIn = false;
            authService.loginSimpleUser(email, password);
        } catch (InvalidLoginDataException invalidLoginEx) {
            if (invalidLoginEx.isInvalidEmail() || invalidLoginEx.isInvalidPassword()) {
                this.message = invalidLoginEx.getDescription();
            } else {
                this.message = InvalidLoginDataException.errMessage;
            }
            logger.log(Level.WARNING, this.message, invalidLoginEx);
            return AuthModel.pageName;
        } catch (InternalErrorException internalEx) {
            logger.log(Level.SEVERE, "Internal Error during login.", internalEx);
            errorModel.setError(internalEx.getTitle(), internalEx.getDescription());
            return ErrorModel.pageName;

        } catch (AuthException authEx) {
            logger.log(Level.SEVERE, "Auth Error occured.", authEx);
            errorModel.setError(authEx.getTitle(), authEx.getDescription());
            return ErrorModel.pageName;
        } catch (OAuthException oAuthEx) {
            logger.log(Level.SEVERE, "Error during oAuth Communication", oAuthEx);
            errorModel.setError("Error during OAuth communication.", "Please try again later. Cause: " + oAuthEx.getErrorReason());
            return ErrorModel.pageName;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Unexpected exception caught.", ex);
            errorModel.setUnknownError();
            return ErrorModel.pageName;
        }

        this.message = "";
        loggedIn = true;

        if (prevPage != null) {
            logger.info("Return to previous page: [" + prevPage + "]");
            return prevPage;
        }
        return IndexModel.pageName;
    }

    public String logout() {
        try {
            authService.logout(null);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            loggedIn = false;
            return AuthModel.pageName;
        } catch (InvalidTokenException e) {
            logger.log(Level.WARNING, "Could not logout.", e);
            errorModel.setError(e.getTitle(), e.getDescription());
            return ErrorModel.pageName;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unexpected error during Logout.", e);
            errorModel.setUnknownError();
            return ErrorModel.pageName;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(String prevPage) {
        this.prevPage = prevPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
