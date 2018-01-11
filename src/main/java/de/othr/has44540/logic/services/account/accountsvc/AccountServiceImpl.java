package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SessionScoped
public class AccountServiceImpl implements AccountServiceIF {

    @PersistenceContext
    private EntityManager em;

    public AccountServiceImpl() {

    }

    @Override
    @CheckLogin
    @Transactional
    public List<AbstractAccount> getAccounts(AuthToken authToken) throws AuthException {
        TypedQuery<AbstractAccount> findAllQ = em
                .createQuery("SELECT a FROM AbstractAccount AS a", AbstractAccount.class);
        return findAllQ.getResultList();
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
