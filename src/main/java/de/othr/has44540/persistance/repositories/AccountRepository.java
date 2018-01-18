package de.othr.has44540.persistance.repositories;

import de.othr.has44540.logic.services.account.accountsvc.AccountCase;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.util.SingleIdEntityRepository;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SessionScoped
@Transactional
public class AccountRepository extends SingleIdEntityRepository<Long, AbstractAccount> implements Serializable {

    public static final Logger logger = Logger.getLogger(AccountRepository.class.getName());

    @PersistenceContext(unitName = SingleIdEntityRepository.PERSISTANCE_UNIT_NAME)
    private EntityManager em;

    public AccountRepository() {
        super(AbstractAccount.class);
    }

    @CheckLogin
    public List<AbstractAccount> getAccountsForCase(AccountCase accountCase) throws AuthException {
        List<AbstractAccount> allAccounts = findAll();
        return allAccounts.stream().filter(accountCase::fitsAccount).collect(Collectors.toList());
    }

    public AbstractAccount findByAlias(String alias) {
        TypedQuery<AbstractAccount> byAliasQuery = em
                .createQuery("SELECT ac FROM AbstractAccount AS ac WHERE ac.alias = :alias", AbstractAccount.class)
                .setParameter("alias", alias);
        try {
            return byAliasQuery.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
