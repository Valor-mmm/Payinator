package de.othr.has44540.persistance.entities.account;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collections;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlSeeAlso({DonorAccount.class, SimpleAccount.class})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractAccount extends GeneratedIDEntity {

    // Attributes

    @Column(unique = true, nullable = false)
    private String alias;


    // References - getter/setter

    @XmlTransient
    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.EAGER)
    private Set<Payment> paymentsOut;

    // Methods

    public boolean addPaymentOut(Payment payment) {
        return paymentsOut.add(payment);
    }

    @Override
    public String toString() {
        return alias;
    }

    // Attributes - getter/setter

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }


    // References - getter/setter

    public Set<Payment> getPaymentsOut() {
        return Collections.unmodifiableSet(paymentsOut);
    }

    public void setPaymentsOut(Set<Payment> paymentOut) {
        this.paymentsOut = paymentOut;
    }
}
