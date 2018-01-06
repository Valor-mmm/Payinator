package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.UnknownAccountException;
import de.othr.has44540.logic.services.UnknownPaymentMethodException;
import de.othr.has44540.logic.services.account.AccountServiceUtils;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.math.BigDecimal;


public class PaymentServiceImpl implements PaymentServiceIF, Serializable {


    @PersistenceContext
    private EntityManager em;

    private AccountServiceUtils accountUtils;


    public PaymentServiceImpl() {
        accountUtils = new AccountServiceUtils();
    }


    @Override
    public Payment payDefault(AbstractAccount toAccount, BigDecimal amount) throws
                                                                            UnknownAccountException,
                                                                            UnknownPaymentMethodException {
        return null;
    }

    @Override
    public Payment createPayment(AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod) throws
                                                                                                   UnknownAccountException,
                                                                                                   UnknownPaymentMethodException {
        if (!accountUtils.verifyAccount(fromAccount)) {
            throw  new UnknownAccountException("An account with the primary key does not exist.", fromAccount);
        }

        Payment payment = new Payment(fromAccount, paymentMethod);
        return null;

    }

    @Override
    public Payment initiatePayment(AbstractAccount toAccount, BigDecimal amount) throws
                                                                                 UnknownAccountException,
                                                                                 UnknownPaymentMethodException {
        return null;
    }
}
