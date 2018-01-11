package de.othr.has44540.ui.utils;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifier;
import de.othr.has44540.logic.services.exceptions.auth.InvalidLoginDataException;
import de.othr.has44540.ui.IndexModel;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthModel implements Serializable {

    public static final String pageName = "login";

    @Inject
    @AuthServiceQualifier(AuthServiceCase.SESSION_BASED)
    private AuthServiceIF authService;

    @Inject
    private ErrorModel errorModel;

    private String email;

    private String password;

    private String prevPage;

    private String message;

    public String login() {
        if (email == null || password == null || email.length() < 3 || password.length() < 3) {
            errorModel.setError("Email or password is empty.",
                                "The email or password you entered were empty. Please fill in both fields");
            return ErrorModel.pageName;
        }

        try {
            authService.login(email, password);
        } catch (InvalidLoginDataException e) {
            if (e.isInvalidEmail() || e.isInvalidPassword()) {
                this.message = e.getDescription();
            } else {
                this.message = InvalidLoginDataException.errMessage;
            }
            return AuthModel.pageName;
        } catch (Exception e) {
            errorModel.setUnknownError();
            return ErrorModel.pageName;
        }

        if (prevPage != null) {
            return prevPage;
        }
        return IndexModel.pageName;
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
}
