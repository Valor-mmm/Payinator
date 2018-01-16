package de.othr.has44540.ui.accounts;

import de.othr.has44540.logic.services.account.accountsvc.AccountCase;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("de.othr.has44540.ui.accounts.AccountCaseConverter")
public class AccountCaseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        for (AccountCase accountCase : AccountCase.values()) {
            if (accountCase.getName().equals(s)) {
                return accountCase;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof AccountCase) {
            String name = ((AccountCase) o).getName();
            return name;
        }
        return null;
    }
}
