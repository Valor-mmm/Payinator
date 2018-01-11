package de.othr.has44540.logic.services.exceptions.account;

import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;

public class UnknownPaymentMethodException extends AccountException {

    private Long primaryKey;

    private static final String excName = "Given payment method was not found.";

    public UnknownPaymentMethodException(String message) {
        super(excName, message);
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
