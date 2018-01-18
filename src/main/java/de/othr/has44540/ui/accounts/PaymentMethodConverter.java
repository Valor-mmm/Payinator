package de.othr.has44540.ui.accounts;

import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.repositories.PaymentMethodRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class PaymentMethodConverter implements Converter, Serializable {

    @Inject
    private PaymentMethodRepository pmRepository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        return pmRepository.findByName(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }

        if (!(o instanceof AbstractPaymentMethod)) {
            return null;
        }

        return ((AbstractPaymentMethod) o).getName();
    }
}
