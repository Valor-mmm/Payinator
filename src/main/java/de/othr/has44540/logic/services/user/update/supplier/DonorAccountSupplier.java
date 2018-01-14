package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.DonationRank;
import de.othr.has44540.persistance.entities.account.impl.DonorAccount;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

public class DonorAccountSupplier implements Supplier<DonorAccount> {

    @Inject
    @DefaultAccAliasQualifier
    private Supplier<String> defaultAccAliasSupplier;

    @Inject
    private Supplier<DonationRank> donationRankSupplier;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public DonorAccount get() {
        DonorAccount donorAccount = new DonorAccount();
        donorAccount.setPaymentsOut(new TreeSet<>());
        donorAccount.setAlias(defaultAccAliasSupplier.get());

        Set<DonationRank> donationRankSet = new TreeSet<>();
        DonationRank initialRank = donationRankSupplier.get();
        initialRank.addDonorAccount(donorAccount);
        donationRankSet.add(initialRank);
        donorAccount.setDonationRanks(donationRankSet);

        DonationRank mergedRank = em.merge(initialRank);
        em.persist(mergedRank);

        em.persist(donorAccount);

        return donorAccount;
    }
}
