package de.othr.has44540.persistance.entities.account.impl;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.DonationRank;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Entity
public class DonorAccount extends AbstractAccount{

    // References

    @ManyToMany
    private Set<DonationRank> donationRanks;


    // References - getter/setter

    public Collection<DonationRank> getDonationRanks() {
        return donationRanks;
    }

    public void setDonationRanks(Set<DonationRank> donationRanks) {
        this.donationRanks = donationRanks;
    }
}
