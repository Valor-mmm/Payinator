package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.persistance.entities.account.AbstractAccount;

import java.util.List;

public interface AccountServiceIF {

    public List<AbstractAccount> getAccounts();

    public AbstractAccount searchAccount(String alias);

}
