package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.DonationRank;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@EntitySupplierQualifier(EntitySupplierCase.DONATION_RANK)
public class DonationRankSupplier implements Supplier<DonationRank> , Serializable{

    private static final Logger logger = Logger.getLogger(DonationRankSupplier.class.getName());

    @PersistenceContext
    private EntityManager em;

    private DonationRank donationRank;

    @PostConstruct
    @Transactional
    private void initDonationRankSupplier() {
        logger.info("Starting Donation Rank supplier.");
        TypedQuery<DonationRank> initialDonationRank = em
                .createQuery("SELECT dr FROM DonationRank AS dr WHERE dr.rank = 0", DonationRank.class);
        try {
            donationRank = initialDonationRank.getSingleResult();
        } catch (NoResultException ex) {
            logger.log(Level.CONFIG, "No initial donation rank found.", ex);
        }
        logger.info("Finished initializing donation rank supplier.");
    }

    @Override
    @Transactional
    public DonationRank get() {
        if (donationRank != null) {
            return donationRank;
        }

        DonationRank initialRank = new DonationRank();

        initialRank.setRank(0);
        initialRank.setName("Beginner");
        initialRank.setDonorAccounts(new TreeSet<>());

        donationRank = initialRank;
        em.persist(donationRank);
        return donationRank;
    }
}
