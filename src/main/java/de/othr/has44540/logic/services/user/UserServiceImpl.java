package de.othr.has44540.logic.services.user;

import de.othr.has44540.logic.services.account.accountsvc.InternalAccountSvcIF;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.repositories.PaymentRepository;
import org.jetbrains.annotations.NotNull;
import sun.rmi.runtime.Log;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
public class UserServiceImpl implements UserServiceIF, UpdatableAuthService {

    @Inject
    @DetectAutomatically
    private AuthServiceIF authService;

    public static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    private InternalAccountSvcIF accountSvc;

    @Override
    @Transactional
    @CheckLogin
    public AbstractUser deleteUser(AuthToken authToken) throws AuthException {
        AbstractUser user = authService.getLoggedInUser();
        logger.info("Removing user: [" + user.getUsername() + "]");

        preparePayments(user);
        deletePaymentMethods(user);
        AbstractUser mergedUser = em.merge(user);
        em.remove(mergedUser);
        return mergedUser;
    }

    private void preparePayments(AbstractUser user) throws AuthException {
        if (user == null) {
            return;
        }

        List<SimpleAccount> allSimpleAccountsOfUser = accountSvc.getAllSimpleAccountsForUser();
        allSimpleAccountsOfUser.forEach(this::deleteAccountFromPayments);
    }

    private void deleteAccountFromPayments(@NotNull SimpleAccount account) {
        if (account.getPaymentsOut() != null) {
            account.getPaymentsOut().forEach(this::deletePaymentOutFromPayment);
        }
        deletePaymentInFromPayment(account);
    }

    @Transactional
    private void deletePaymentInFromPayment(@NotNull SimpleAccount account) {
        if (account.getPaymentsIn() == null) {
            return;
        }

        account.getPaymentsIn().forEach(payment -> {
            Payment mergedPayment = em.merge(payment);
            mergedPayment.setToAccount(null);
            em.persist(payment);
        });
    }

    @Transactional
    private void deletePaymentOutFromPayment(Payment payment) {
        Payment mergedPayment = em.merge(payment);
        mergedPayment.setFromAccount(null);
        mergedPayment.setPaymentMethod(null);
        em.persist(mergedPayment);
    }

    @Transactional
    private void deletePaymentMethods(AbstractUser user) {
        if (user == null || user.getPaymentMethods() == null) {
            return;
        }

        user.getPaymentMethods().forEach(paymentMethod -> {
            AbstractPaymentMethod pm = em.merge(paymentMethod);
            em.remove(pm);
        });

    }

    @Override
    public void updateAuthService(AuthServiceIF newAuthService) {
        this.authService = newAuthService;
    }

    @Override
    public void listenOnUpdateEvent(@Observes ServiceUpdate serviceUpdate) {
        updateAuthService(serviceUpdate.getNewService());
    }
}
