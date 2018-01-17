package de.othr.has44540.persistance.repositories;

import de.othr.has44540.logic.services.account.accountsvc.AccountCase;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.util.SingleIdEntityRepository;

import javax.enterprise.context.SessionScoped;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Transactional
public class AccountRepository extends SingleIdEntityRepository<Long, AbstractAccount> implements Serializable {

    public List<AbstractAccount> getAccountsForCase(AccountCase accountCase) {
        return findAll()
                .stream()
                .filter(accountCase::fitsAccout)
                .collect(Collectors.toList());
    }

}