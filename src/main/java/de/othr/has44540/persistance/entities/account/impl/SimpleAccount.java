package de.othr.has44540.persistance.entities.account.impl;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

@Entity
public class SimpleAccount extends AbstractAccount{

    // Attributes

    private Boolean isDefault;

    @Column(nullable = false)
    private BigDecimal balance;


    // References

    @ManyToOne
    private DonorAccount donorAccount;

    @OneToMany(mappedBy = "toAccount")
    private Set<Payment> paymentsIn;


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
