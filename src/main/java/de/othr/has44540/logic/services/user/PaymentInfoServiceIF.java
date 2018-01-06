package de.othr.has44540.logic.services.user;

import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

import java.util.List;

public interface PaymentInfoServiceIF {

    List<AbstractPaymentMethod> getPaymentMethods();
}
