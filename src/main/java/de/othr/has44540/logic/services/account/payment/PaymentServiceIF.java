package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.auth.InvalidLoginException;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.logic.services.InvalidAccountException;
import de.othr.has44540.logic.services.UnknownPaymentMethodException;

import java.io.Serializable;
import java.math.BigDecimal;

public interface PaymentServiceIF extends Serializable{

    Payment payDefault(AuthToken authToken, AbstractAccount toAccount, BigDecimal amount)
            throws InvalidAccountException, UnknownPaymentMethodException, InvalidLoginException;

    Payment createPayment(AuthToken authToken, AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod)
            throws InvalidAccountException, UnknownPaymentMethodException, InvalidLoginException;

    Payment initiatePayment(AuthToken authToken, Payment payment, AbstractAccount toAccount, BigDecimal amount)
            throws InvalidAccountException, UnknownPaymentMethodException, InvalidLoginException;

}
