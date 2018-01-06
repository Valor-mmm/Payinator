package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.persistance.entities.account.AbstractAccount;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class AccountServiceImpl implements AccountServiceIF, Serializable {

    @PersistenceContext
    private EntityManager em;

    public AccountServiceImpl() {

    }

    @Override
    public List<AbstractAccount> getAccounts() {
        TypedQuery<AbstractAccount> findAllQ = em.createQuery("SELECT a FROM AbstractAccount AS a",
                AbstractAccount.class);
        return findAllQ.getResultList();
    }

    @Override
    public AbstractAccount searchAccount(String alias) {
        TypedQuery<AbstractAccount> searchQ = em.createQuery("SELECT a FROM AbstractAccount AS a WHERE a.alias = :alias"
                , AbstractAccount.class);
        searchQ.setParameter("alias", alias);
        return searchQ.getSingleResult();
    }
}
