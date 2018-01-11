package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.InvalidAccountException;
import de.othr.has44540.logic.services.UnknownPaymentMethodException;
import de.othr.has44540.logic.services.account.AccountServiceUtils;
import de.othr.has44540.logic.services.auth.InvalidLoginException;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;

@SessionScoped
public class PaymentServiceImpl implements PaymentServiceIF {


    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountServiceUtils accountUtils;

    @Override
    @CheckLogin
    @Transactional
    public Payment payDefault(AuthToken authToken, AbstractAccount toAccount, BigDecimal amount) throws
                                                                                                 InvalidAccountException,
                                                                                                 UnknownPaymentMethodException,
                                                                                                 InvalidLoginException {
        accountUtils.checkSimpleAccount(toAccount);
        AbstractAccount fromAccount = accountUtils.getDefaultAccount();
        if (fromAccount == null) {
            throw new InvalidAccountException("User does not have a default account.");
        }
        AbstractPaymentMethod paymentMethod = accountUtils.getDefaultPaymentMethod();
        if (paymentMethod == null) {
            throw new UnknownPaymentMethodException("User does not have a default payment method.");
        }

        Payment payment = createPayment(authToken, fromAccount, paymentMethod);
        return initiatePayment(authToken, payment, toAccount, amount);
    }

    @Override
    @CheckLogin
    @Transactional
    public Payment createPayment(AuthToken authToken, AbstractAccount fromAccount,
                                 AbstractPaymentMethod paymentMethod) throws
                                                                      InvalidAccountException,
                                                                      UnknownPaymentMethodException,
                                                                      InvalidLoginException {
        accountUtils.checkAccount(fromAccount);
        accountUtils.checkAccountOwner(fromAccount);
        if (!accountUtils.verifyPaymentMethodOfUser(paymentMethod)) {
            throw new UnknownPaymentMethodException("No paymentMethod for user with id found.", paymentMethod);
        }

        return new Payment(fromAccount, paymentMethod);
    }

    @Override
    @CheckLogin
    @Transactional
    public Payment initiatePayment(AuthToken authToken, Payment payment, AbstractAccount toAccount,
                                   BigDecimal amount) throws
                                                      InvalidAccountException,
                                                      UnknownPaymentMethodException,
                                                      InvalidLoginException {
        accountUtils.checkSimpleAccount(toAccount);

        SimpleAccount simpleAccount = (SimpleAccount) toAccount;
        payment.setToAccount(simpleAccount);
        payment.setAmount(amount);

        if (accountUtils.isCompanyTransaction()) {
            bookTransaction(amount);
        }

        em.persist(payment);
        return payment;
    }

    private void bookTransaction(BigDecimal amount) {
        /* TODO: book transaction to company account
            Maybe donate a fractural amount
        */
    }
}
