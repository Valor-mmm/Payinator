package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.account.AccountServiceUtils;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.account.AccountException;
import de.othr.has44540.logic.services.exceptions.account.InvalidAccountException;
import de.othr.has44540.logic.services.exceptions.account.UnknownPaymentMethodException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.logging.Logger;

@SessionScoped
@WebService
public class PaymentService implements PaymentServiceIF {

    public static final Logger logger = Logger.getLogger(PaymentService.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountServiceUtils accountUtils;

    @Override
    @CheckLogin
    @Transactional
    public Payment payDefault(AuthToken authToken, Payment payment) throws AuthException, AccountException {
        if (payment == null) {
            throw new AccountException("The payment is null.", "The payment for default payment is null");
        }
        if (payment.getToAccount() == null) {
            AbstractAccount defaultAccount = accountUtils.determinDefaultAccount();
            accountUtils.checkSimpleAccount(defaultAccount);
            payment.setToAccount((SimpleAccount) defaultAccount);
        }
        payment.setFromAccount(accountUtils.getDefaultAccount());
        if (payment.getFromAccount() == null) {
            throw new InvalidAccountException("User does not have a default account.");
        }
        payment.setPaymentMethod(accountUtils.getDefaultPaymentMethod());
        if (payment.getPaymentMethod() == null) {
            throw new UnknownPaymentMethodException("User does not have a default payment method.");
        }

        return makePayment(authToken, payment);
    }

    @Override
    @CheckLogin
    @Transactional
    public Payment makePayment(AuthToken authToken, Payment payment) throws AuthException, AccountException {
        accountUtils.checkAccount(payment.getFromAccount());
        accountUtils.verifyAccount(payment.getToAccount());
        accountUtils.checkAccountOwner(payment.getFromAccount());
        if (payment.getFromAccount().getAlias().equals(payment.getToAccount().getAlias())) {
            throw new AccountException("From and to Account are equal.",
                                       "The from and to account for this payment are equal");
        }
        if (!accountUtils.verifyPaymentMethodOfUser(payment.getPaymentMethod())) {
            throw new UnknownPaymentMethodException("No paymentMethod for user with id found.",
                                                    payment.getPaymentMethod());
        }

        return initiatePayment(payment);
    }

    @WebMethod(exclude = true)
    private Payment initiatePayment(Payment payment) throws AccountException, AuthException {
        accountUtils.checkSimpleAccount(payment.getToAccount());

        if (payment.getAmount() == null) {
            throw new AccountException("The payment amount was null", "The amount of the given payment is null.");
        }

        if (payment.getId() != null) {
            throw new AccountException("The payment has an ID",
                                       "The id sould be system given and null at creation time.");
        }

        if (accountUtils.isCompanyTransaction()) {
            bookTransaction(payment.getAmount());
        }

        payment = em.merge(payment);
        em.persist(payment);

        SimpleAccount mergedToAcc = em.merge(payment.getToAccount());
        AbstractAccount mergedFromAcc = em.merge(payment.getFromAccount());
        mergedToAcc.addPaymentIn(payment);
        mergedFromAcc.addPaymentOut(payment);

        em.persist(mergedToAcc);
        em.persist(mergedFromAcc);

        return payment;
    }

    @WebMethod(exclude = true)
    private void bookTransaction(BigDecimal amount) {
        /* TODO: book transaction to company account
            Maybe donate a fractural amount
        */
    }
}
