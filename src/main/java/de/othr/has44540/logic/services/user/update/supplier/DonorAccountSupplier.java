package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.DonationRank;
import de.othr.has44540.persistance.entities.account.impl.DonorAccount;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

@ApplicationScoped
@EntitySupplierQualifier(EntitySupplierCase.DONOR_ACCOUNT)
public class DonorAccountSupplier implements Supplier<DonorAccount>, Serializable {

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.DEFAULT_ACC_ALIAS)
    private Supplier<String> defaultAccAliasSupplier;

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.DONATION_RANK)
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

        em.persist(donorAccount);
        initialRank.addDonorAccount(donorAccount);
        donationRankSet.add(initialRank);
        donorAccount.setDonationRanks(donationRankSet);

        DonationRank mergedRank = em.merge(initialRank);
        em.persist(mergedRank);

        em.persist(donorAccount);

        return donorAccount;
    }
}
