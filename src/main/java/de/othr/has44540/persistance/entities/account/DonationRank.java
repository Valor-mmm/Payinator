package de.othr.has44540.persistance.entities.account;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class DonationRank extends GeneratedIDEntity{

    // Attributes

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Integer rank;


    // References

    @XmlTransient
    @ManyToMany(mappedBy = "donationRanks", fetch = FetchType.EAGER)
    private Set<DonorAccount> donorAccounts;

    // Methods

    public void addDonorAccount(DonorAccount account) {
        if (this.donorAccounts == null) {
            this.donorAccounts = new TreeSet<>();
        }
        this.donorAccounts.add(account);
    }

    // Attributes -getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }


    // References - getter/setter

    public Set<DonorAccount> getDonorAccounts() {
        return Collections.unmodifiableSet(donorAccounts);
    }

    public void setDonorAccounts(Set<DonorAccount> donorAccounts) {
        this.donorAccounts = donorAccounts;
    }
}
