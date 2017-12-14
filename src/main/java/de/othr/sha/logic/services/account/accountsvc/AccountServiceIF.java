package de.othr.sha.logic.services.account.accountsvc;

import de.othr.sha.persistance.entities.account.AbstractAccount;

public interface AccountServiceIF {

    public AbstractAccount getAccounts();

    public AbstractAccount searchAccount(String alias);

}
