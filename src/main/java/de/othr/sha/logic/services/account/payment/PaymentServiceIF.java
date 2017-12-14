package de.othr.sha.logic.services.account.payment;

import de.othr.sha.persistance.entities.account.AbstractAccount;
import de.othr.sha.persistance.entities.account.Payment;
import de.othr.sha.persistance.entities.user.PaymentInformation.AbstractPaymentMethod;
import de.othr.sha.logic.services.UnknownAccountException;
import de.othr.sha.logic.services.UnknownPaymentMethodException;

import java.math.BigDecimal;

public interface PaymentServiceIF {

    public Payment payDefault(AbstractAccount toAccount, BigDecimal amount)
            throws UnknownAccountException, UnknownPaymentMethodException;

    public Payment createPayment(AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod)
            throws UnknownAccountException, UnknownPaymentMethodException;

    public Payment initiatePayment(AbstractAccount toAccount, BigDecimal amount)
            throws UnknownAccountException, UnknownPaymentMethodException;

}
