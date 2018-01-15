package de.othr.has44540.persistance.entities.account;

import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractAccount extends GeneratedIDEntity {

    // Attributes

    @Column(unique = true, nullable = false)
    private String alias;


    // References - getter/setter

    @OneToMany(mappedBy = "fromAccount", fetch = FetchType.EAGER)
    private Set<Payment> paymentsOut;


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
