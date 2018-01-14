package de.othr.has44540.persistance.entities.user.paymentInformation;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends AbstractPaymentMethod{

    // Attributes

    @Column(nullable = false)
    private String cardProvider;

    @Column(nullable = false)
    private Long securityNumber;

    @Column(nullable = false)
    private String securityCode;


    // Attributes -getter/setter

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public Long getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(Long securityNumber) {
        this.securityNumber = securityNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
