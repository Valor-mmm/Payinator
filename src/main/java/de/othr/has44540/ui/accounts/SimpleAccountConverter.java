package de.othr.has44540.ui.accounts;

import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.repositories.AccountRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

@SessionScoped
public class SimpleAccountConverter implements Converter, Serializable {

    @Inject
    private AccountRepository accountRepository;

    private static final Logger logger = Logger.getLogger(AccountConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }

        try {
            Object account = accountRepository.findByAlias(s);
            if (!(account instanceof SimpleAccount)) {
                logger.warning("Found account was no simple account");
                account = null;
            }
            logger.info("Converting: [" + s + "] to object. Found: " + (account != null));
            return account;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }

        if (!(o instanceof SimpleAccount)) {
            return null;
        }

        return ((SimpleAccount) o).getAlias();
    }
}
