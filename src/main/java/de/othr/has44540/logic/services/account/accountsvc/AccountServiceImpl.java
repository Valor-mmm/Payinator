package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.SessionBasedAuthService;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.repositories.AccountRepository;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@WebService
public class AccountServiceImpl implements AccountServiceIF {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private AccountRepository repository;

    @Override
    @CheckLogin
    @Transactional
    public List<AbstractAccount> getAccounts(AuthToken authToken) throws AuthException {
        return repository.findAll();
    }

    @Override
    @CheckLogin
    @Transactional
    public AbstractAccount searchAccount(AuthToken authToken, String alias) throws AuthException {
        TypedQuery<AbstractAccount> searchQ = em
                .createQuery("SELECT a FROM AbstractAccount AS a WHERE a.alias = :alias", AbstractAccount.class);
        searchQ.setParameter("alias", alias);
        return searchQ.getSingleResult();
    }
}
