package de.othr.has44540.ui.accounts;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.repositories.AccountRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.logging.Logger;

@SessionScoped
public class AccountConverter implements Converter, Serializable {

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

        if (!(o instanceof AbstractAccount)) {
            return null;
        }

        return ((AbstractAccount) o).getAlias();
    }
}
