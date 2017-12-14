package de.othr.sha.persistance.entities.account;

import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
public class AbstractAccount extends GeneratedIDEntity {

    // Attributes

    @Column(unique = true, nullable = false)
    private String alias;


    // References - getter/setter

    @OneToMany(mappedBy = "fromAccount")
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
