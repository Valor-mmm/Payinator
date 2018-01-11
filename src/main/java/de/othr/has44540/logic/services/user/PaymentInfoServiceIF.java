package de.othr.has44540.logic.services.user;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import java.io.Serializable;
import java.util.List;

public interface PaymentInfoServiceIF extends Serializable{

    List<AbstractPaymentMethod> getPaymentMethods(AuthToken authToken) throws AuthException, InternalErrorException;
}
