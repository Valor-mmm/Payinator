package de.othr.has44540.logic.services.account;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.account.AccountException;
import de.othr.has44540.logic.services.exceptions.account.InvalidAccountException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.roles.Company;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@SessionScoped
public class AccountServiceUtils implements Serializable, UpdatableAuthService {

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

    public void checkAccount(AbstractAccount account) throws InvalidAccountException {
        if (!verifyAccount(account)) {
            throw new InvalidAccountException("An account with the id does not exist.", account);
        }
    }

    public void checkAccountOwner(AbstractAccount account) throws InvalidAccountException {
        AbstractUser user = authService.getExecutiveUser();
        if (!user.containsAccount(account)) {
            throw new InvalidAccountException("The current executive user does not have the account.", account);
        }
    }

    public void checkSimpleAccount(AbstractAccount account) throws InvalidAccountException {
        checkAccount(account);
        if (!(account instanceof SimpleAccount)) {
            throw new InvalidAccountException("Can not send money to a donation account.", account);
        }
    }

    public boolean verifyPaymentMethodOfUser(AbstractPaymentMethod paymentMethod) {
        AbstractUser user = authService.getExecutiveUser();
        TypedQuery<AbstractUser> methodOfUserQ = em.createQuery(
                "SELECT u FROM AbstractUser u JOIN u.paymentMethods p WHERE u.id = :userId AND p.id = :pmId",
                AbstractUser.class);
        methodOfUserQ.setParameter("userId", user.getId());
        methodOfUserQ.setParameter("pmId", paymentMethod.getId());
        try {
            methodOfUserQ.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
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

    public AbstractAccount determinDefaultAccount() throws AccountException {
        AbstractUser user = authService.getLoggedInUser();
        AbstractAccount account = user.getDefaultAccount();
        if (account == null) {
            throw new AccountException("No default account found.",
                                       "The logged in user does not have an default account");
        }
        return account;
    }

    @Override
    public void updateAuthService(AuthServiceIF newAuthService) {
        this.authService = newAuthService;
    }

    @Override
    public void listenOnUpdateEvent(@Observes ServiceUpdate serviceUpdate) {
        this.updateAuthService(serviceUpdate.getNewService());
    }
}
