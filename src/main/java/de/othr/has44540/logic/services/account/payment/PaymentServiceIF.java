package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.logic.services.InvalidAccountException;
import de.othr.has44540.logic.services.UnknownPaymentMethodException;

import java.math.BigDecimal;

public interface PaymentServiceIF {

    Payment payDefault(AuthToken authToken, AbstractAccount toAccount, BigDecimal amount)
            throws InvalidAccountException, UnknownPaymentMethodException;

    Payment createPayment(AuthToken authToken, AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod)
            throws InvalidAccountException, UnknownPaymentMethodException;

    Payment initiatePayment(AuthToken authToken, Payment payment, AbstractAccount toAccount, BigDecimal amount)
            throws InvalidAccountException, UnknownPaymentMethodException;

}
