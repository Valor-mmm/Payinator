package de.othr.has44540.logic.services.user;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SessionScoped
public class PaymentInfoServiceImpl implements PaymentInfoServiceIF {

    @PersistenceContext
    private EntityManager em;

    @Override
    @CheckLogin
    public List<AbstractPaymentMethod> getPaymentMethods(AuthToken authToken) throws AuthException {
        TypedQuery<AbstractPaymentMethod> paymentMethodQuery = em
                .createQuery("SELECT pm FROM AbstractPaymentMethod AS pm", AbstractPaymentMethod.class);
        return paymentMethodQuery.getResultList();
    }
}
