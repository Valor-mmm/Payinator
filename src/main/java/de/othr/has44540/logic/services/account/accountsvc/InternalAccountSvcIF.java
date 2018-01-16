package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

import java.util.List;

public interface InternalAccountSvcIF extends AccountServiceIF{

    AccountCase determineAccountCase(AbstractAccount account) throws AuthException;

    List<AbstractAccount> getAccountsByCase(AccountCase accountCase) throws AuthException, InternalErrorException;

}
