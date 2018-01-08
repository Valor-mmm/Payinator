package de.othr.has44540.logic.services;

import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

public class UnknownPaymentMethodException extends RuntimeException {

    private Long primaryKey;

    public UnknownPaymentMethodException(String message) {
        super(message);
    }

    public UnknownPaymentMethodException(Long primaryKey) {
        super();
        this.primaryKey = primaryKey;
    }

    public UnknownPaymentMethodException(AbstractPaymentMethod paymentMethod) {
        super();
        if (paymentMethod == null) {
            this.primaryKey = (long) -1;
        } else {
            this.primaryKey = paymentMethod.getId();
        }
    }

    public UnknownPaymentMethodException(String message, Long primaryKey) {
        this(message);
        this.primaryKey = primaryKey;
    }

    public UnknownPaymentMethodException(String message, AbstractPaymentMethod paymentMethod) {
        this(message);
        if (paymentMethod == null) {
            this.primaryKey = (long) -1;
        } else {
            this.primaryKey = paymentMethod.getId();
        }
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
}
