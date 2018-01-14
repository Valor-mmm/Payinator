package de.othr.has44540.logic.services.user.update.supplier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@EntitySupplierQualifier(EntitySupplierCase.DEFAULT_ACC_ALIAS)
public class DefaultAccAliasSupplier implements Supplier<String>, Serializable {

    private static final Logger logger = Logger.getLogger(DefaultAccAliasSupplier.class.getName());

    @PersistenceContext
    private EntityManager em;

    private Long lastDefaultIndex = new Long(0);

    private final static String name = "Default";

    @PostConstruct
    @Transactional
    public void initDefaultAccountAliasSupplier() {
        logger.info("Starting Default account alias supplier.");
        boolean emExists = false;
        if (em != null) {
            emExists = true;
        }
        TypedQuery<String> allDefaultAccounts = em.createQuery(
                "SELECT aa.alias FROM AbstractAccount AS aa WHERE aa.alias LIKE CONCAT(:defaultName, '%') ORDER BY aa.alias DESC",
                String.class);
        allDefaultAccounts.setParameter("defaultName", name);
        List<String> defaultNames = allDefaultAccounts.getResultList();
        for (String alias : defaultNames) {
            logger.info(alias);
            try {
                lastDefaultIndex = Long.parseLong(alias);
                break;
            } catch (NumberFormatException ex) {
                logger.log(Level.CONFIG, "Default account alias was not an long", ex);
            }
        }
        logger.info("Last Default alias for account was titled with index: [" + lastDefaultIndex + "]");
    }

    @Override
    public String get() {
        return name + ++lastDefaultIndex;
    }
}
