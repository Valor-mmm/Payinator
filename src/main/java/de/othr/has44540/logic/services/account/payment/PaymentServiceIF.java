package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.logic.services.UnknownAccountException;
import de.othr.has44540.logic.services.UnknownPaymentMethodException;

import java.math.BigDecimal;

public interface PaymentServiceIF {

    public Payment payDefault(AbstractAccount toAccount, BigDecimal amount)
            throws UnknownAccountException, UnknownPaymentMethodException;

    public Payment createPayment(AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod)
            throws UnknownAccountException, UnknownPaymentMethodException;

    public Payment initiatePayment(AbstractAccount toAccount, BigDecimal amount)
            throws UnknownAccountException, UnknownPaymentMethodException;

}
