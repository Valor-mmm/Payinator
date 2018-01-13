package de.othr.has44540.logic.services.user.update.updater;

import de.othr.external.services.oauth.korbinianSchmidt.data.PaymentMethod;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterCase;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterQalifier;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@EntityUpdaterQalifier(EntityUpdaterCase.PAYMENT_INFORMATION)
public class PaymentMethodUpdater {

    @PersistenceContext
    private EntityManager em;

    public Set<AbstractPaymentMethod> updatePaymentMethods(List<PaymentMethod> newPaymentMethods,
                                                           Set<AbstractPaymentMethod> oldPaymentMethods) {

    }


}
