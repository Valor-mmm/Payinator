package de.othr.sha.persistance.entities.account.impl;

import de.othr.sha.persistance.entities.account.AbstractAccount;
import de.othr.sha.persistance.entities.account.DonationRank;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class DonorAccount extends AbstractAccount{

    // References

    @ManyToMany
    private Collection<DonationRank> donationRanks;


    // References - getter/setter

    public Collection<DonationRank> getDonationRanks() {
        return donationRanks;
    }

    public void setDonationRanks(Collection<DonationRank> donationRanks) {
        this.donationRanks = donationRanks;
    }
}
