package de.othr.has44540.ui;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class IndexModel implements Serializable {

    private static final Logger logger = Logger.getLogger(IndexModel.class.getName());

    @Inject
    @AuthServiceQualifier(AuthServiceCase.SESSION_BASED)
    private AuthServiceIF authService;

    private String username;

    public static final String pageName = "index";

    @PostConstruct
    public void initIndexModel() {
        if (authService.getLoggedInUser() != null) {
            username = authService.getLoggedInUser().getUsername();
        }
    }

    public static void redirectToPage(String page) {
        try {
            ExternalContext exContext = FacesContext.getCurrentInstance().getExternalContext();
            exContext.dispatch(page + ".xhtml");
        } catch (Exception e1) {
            logger.log(Level.WARNING, "Could not dispatch page [" + page + "]");
        }
    }

    public String getUsername() {
        return username;
    }

    public String showUsername() {
        initIndexModel();
        if (username == null) {
            return "hidden";
        }
        return "visible";
    }

    public String showNotLoggedIn() {
        initIndexModel();
        if (username != null) {
            return "hidden";
        }
        return "visible";
    }
}
