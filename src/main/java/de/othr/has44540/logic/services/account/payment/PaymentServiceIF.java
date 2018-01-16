package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.account.AccountException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import java.io.Serializable;
import java.math.BigDecimal;

public interface PaymentServiceIF extends Serializable {

    // AbstractAccount toAccount, BigDecimal amount, String cause
    Payment payDefault(AuthToken authToken, Payment payment) throws AuthException, AccountException;

    // AbstractAccount fromAccount, AbstractAccount toAccount, AbstractPaymentMethod paymentMethod, BigDecimal
    // amount, String cause
    Payment makePayment(AuthToken authToken, Payment payment) throws AuthException, AccountException;

}
