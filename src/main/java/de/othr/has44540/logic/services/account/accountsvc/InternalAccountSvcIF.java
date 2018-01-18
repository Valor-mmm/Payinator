package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;

import java.util.List;

public interface InternalAccountSvcIF extends AccountServiceIF {

    List<AbstractAccount> getAccountsByUserAndCase(AccountCase accountCase) throws AuthException;

    List<SimpleAccount> getAllSimpleAccountsForUser() throws AuthException;
}
