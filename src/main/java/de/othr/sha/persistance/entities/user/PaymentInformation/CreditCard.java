package de.othr.sha.persistance.entities.user.PaymentInformation;

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
    private Integer securityCode;


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

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }
}
