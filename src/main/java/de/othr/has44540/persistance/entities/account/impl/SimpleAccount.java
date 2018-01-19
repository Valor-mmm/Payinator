package de.othr.has44540.persistance.entities.account.impl;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlSeeAlso({CompanyAccount.class, CharityAccount.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleAccount extends AbstractAccount {

    // Attributes

    private Boolean isDefault;

    @Column(nullable = false)
    private BigDecimal balance;


    // References

    @XmlTransient
    @ManyToOne(optional = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private DonorAccount donorAccount;

    @XmlTransient
    @OneToMany(mappedBy = "toAccount", fetch = FetchType.EAGER)
    private Set<Payment> paymentsIn;


    public SimpleAccount() {
        balance = new BigDecimal(0);
    }

    // Methods

    public boolean addPaymentIn(Payment payment) {
        boolean result = paymentsIn.add(payment);
        if (result) {
            balance = balance.add(payment.getAmount());
        }
        return result;
    }

    public BigDecimal addPaymentOut(Payment payment) {
        BigDecimal result = super.addPaymentOut(payment);
        if (result == null) {
            return null;
        }
        if (balance.subtract(payment.getAmount()).compareTo(new BigDecimal(0)) >= 0) {
            balance = balance.subtract(payment.getAmount());
            return new BigDecimal(0);
        } else {
            BigDecimal rest = balance.subtract(payment.getAmount()).negate();
            balance = new BigDecimal(0);
            return rest;
        }
    }

    // Attributes - getter/setter

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    // References -getter/setter


    public DonorAccount getDonorAccount() {
        return donorAccount;
    }

    public void setDonorAccount(DonorAccount donorAccount) {
        this.donorAccount = donorAccount;
    }

    public Set<Payment> getPaymentsIn() {
        return Collections.unmodifiableSet(paymentsIn);
    }

    public void setPaymentsIn(Set<Payment> paymentsIn) {
        this.paymentsIn = paymentsIn;
    }
}
