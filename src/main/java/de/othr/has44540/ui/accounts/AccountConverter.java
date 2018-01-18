package de.othr.has44540.ui.accounts;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.repositories.AccountRepository;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("de.othr.has44540.ui.accounts.AccountConverter")
public class AccountConverter implements Converter {

    @Inject
    private AccountRepository accountRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }

        return accountRepository.findByAlias(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }

        if (!(o instanceof AbstractAccount)) {
            return null;
        }

        return ((AbstractAccount) (AbstractAccount) o).getAlias();
    }
}
