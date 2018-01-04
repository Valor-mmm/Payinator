package de.othr.has44540.persistance.entities.account;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collections;
import java.util.Set;

@Entity
public class DonationRank extends GeneratedIDEntity{

    // Attributes

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Integer rank;


    // References

    @ManyToMany(mappedBy = "donationRanks")
    private Set<DonorAccount> donorAccounts;


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
