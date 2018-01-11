package de.othr.has44540.logic.services.account;

import de.othr.has44540.logic.services.exceptions.account.InvalidAccountException;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.roles.Company;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@SessionScoped
public class AccountServiceUtils implements Serializable {

    @PersistenceContext
    private EntityManager em;

    @Inject
    @DetectAutomatically
    private AuthServiceIF authService;

    public AccountServiceUtils() {

    }

    public boolean verifyAccount(AbstractAccount account) {
        AbstractAccount result = em.find(AbstractAccount.class, account.getId());
        return result != null;
    }

    public void checkAccount(AbstractAccount account) {
        if (!verifyAccount(account)) {
            throw new InvalidAccountException("An account with the id does not exist.", account);
        }
    }

    public void checkAccountOwner(AbstractAccount account) {
        AbstractUser user = authService.getExecutiveUser();
        if (!user.containsAccount(account)) {
            throw new InvalidAccountException("The current executive user does not have the account.", account);
        }
    }

    public void checkSimpleAccount(AbstractAccount account) {
        checkAccount(account);
        if (!(account instanceof SimpleAccount)) {
            throw new InvalidAccountException("Can not send money to a donation account.", account);
        }
    }

    public boolean verifyPaymentMethodOfUser(AbstractPaymentMethod paymentMethod) {
        AbstractUser user = authService.getExecutiveUser();
        TypedQuery<AbstractPaymentMethod> methodOfUserQ = em.createQuery(
                "SELECT u FROM AbstractUser u JOIN u.paymentMethods p WHERE u.id = :userId AND p.id = :pmId",
                AbstractPaymentMethod.class);
        methodOfUserQ.setParameter("userId", user.getId());
        methodOfUserQ.setParameter("pmId", paymentMethod.getId());
        return methodOfUserQ.getSingleResult() != null;
    }

    public boolean isCompanyTransaction() {
        AbstractUser user = authService.getLoggedInUser();
        return user instanceof Company;
    }

    public AbstractAccount getDefaultAccount() {
        AbstractUser user = authService.getExecutiveUser();
        return user.getDefaultAccount();
    }

    public AbstractPaymentMethod getDefaultPaymentMethod() {
        AbstractUser user = authService.getExecutiveUser();
        return user.getDefaultPaymentMethod();
    }
}
