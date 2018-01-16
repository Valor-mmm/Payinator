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

@SessionScoped
@WebService
public class PaymentService implements PaymentServiceIF {


    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountServiceUtils accountUtils;

    @Override
    @CheckLogin
    @Transactional
    public Payment payDefault(AuthToken authToken, AbstractAccount toAccount, BigDecimal amount, String cause) throws
                                                                                                               AuthException,
                                                                                                               AccountException {
        if (toAccount == null) {
            toAccount = accountUtils.determinDefaultAccount();
        }
        accountUtils.checkSimpleAccount(toAccount);
        AbstractAccount fromAccount = accountUtils.getDefaultAccount();
        if (fromAccount == null) {
            throw new InvalidAccountException("User does not have a default account.");
        }
        AbstractPaymentMethod paymentMethod = accountUtils.getDefaultPaymentMethod();
        if (paymentMethod == null) {
            throw new UnknownPaymentMethodException("User does not have a default payment method.");
        }

        return makePayment(authToken, fromAccount, toAccount, paymentMethod, amount, cause);
    }

    @Override
    @CheckLogin
    @Transactional
    public Payment makePayment(AuthToken authToken, AbstractAccount fromAccount, AbstractAccount toAccount,
                               AbstractPaymentMethod paymentMethod, BigDecimal amount, String cause) throws
                                                                                                     AuthException,
                                                                                                     AccountException {
        accountUtils.checkAccount(fromAccount);
        accountUtils.checkAccountOwner(fromAccount);
        if (fromAccount.getAlias().equals(toAccount.getAlias())) {
            throw new AccountException("From and to Account are equal.",
                                       "The from and to account for this payment are equal");
        }
        if (!accountUtils.verifyPaymentMethodOfUser(paymentMethod)) {
            throw new UnknownPaymentMethodException("No paymentMethod for user with id found.", paymentMethod);
        }

        Payment payment = new Payment(fromAccount, paymentMethod);
        payment.setCause(cause);
        return initiatePayment(payment, toAccount, amount);
    }

    @WebMethod(exclude = true)
    private Payment initiatePayment(Payment payment, AbstractAccount toAccount, BigDecimal amount) throws
                                                                                                   AccountException,
                                                                                                   AuthException {
        accountUtils.checkSimpleAccount(toAccount);

        SimpleAccount simpleAccount = (SimpleAccount) toAccount;
        payment.setToAccount(simpleAccount);
        payment.setAmount(amount);

        if (accountUtils.isCompanyTransaction()) {
            bookTransaction(amount);
        }

        em.persist(payment);

        SimpleAccount mergedToAcc = em.merge((SimpleAccount) toAccount);
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
