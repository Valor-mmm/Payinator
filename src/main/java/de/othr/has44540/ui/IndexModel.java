package de.othr.has44540.ui;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class IndexModel implements Serializable {

    private static final Logger logger = Logger.getLogger(IndexModel.class.getName());

    public static final String pageName = "index";

    public static void redirectToPage(String page) {
        try {
            ExternalContext exContext = FacesContext.getCurrentInstance().getExternalContext();
            exContext.dispatch(page + ".xhtml");
        } catch (Exception e1) {
            logger.log(Level.WARNING, "Could not dispatch page [" + page + "]");
        }
    }
}
