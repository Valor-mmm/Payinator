package de.othr.has44540.logic.services.account;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public class AccountServiceUtils implements Serializable{

    @PersistenceContext
    private EntityManager em;

    @Inject @DetectAutomatically
    private AuthServiceIF authService;

    public AccountServiceUtils() {

    }

    public boolean verifyAccount(AbstractAccount account) {
        AbstractAccount result = em.find(AbstractAccount.class, account.getId());
        return result != null;
    }

    @CheckLogin
    public boolean verifyPaymentMethodOfUser(AbstractPaymentMethod paymentMethod) {
        AbstractUser user = authService.getLoggedInUser();
        TypedQuery<AbstractPaymentMethod> methodOfUserQ = em.createQuery("SELECT u FROM AbstractUser u JOIN u.paymentMethods p WHERE u.id = :userId AND p.id = :pmId",
                                                                         AbstractPaymentMethod.class);
        return methodOfUserQ.getSingleResult() != null;
    }
}
