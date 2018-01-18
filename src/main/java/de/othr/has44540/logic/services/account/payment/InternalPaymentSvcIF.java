package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.Payment;

import java.util.List;

public interface InternalPaymentSvcIF extends PaymentServiceIF {

    List<Payment> getIncomingForUser() throws AuthException;

    List<Payment> getOutgoingForUser() throws AuthException;
}
