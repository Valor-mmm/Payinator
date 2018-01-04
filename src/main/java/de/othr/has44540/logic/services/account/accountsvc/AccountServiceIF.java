package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.persistance.entities.account.AbstractAccount;

public interface AccountServiceIF {

    public AbstractAccount getAccounts();

    public AbstractAccount searchAccount(String alias);

}
