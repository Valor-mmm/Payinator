package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

public interface InternalAccountSvcIF extends AccountServiceIF{

    AccountCase determineAccountCase(AbstractAccount account) throws AuthException;

}
