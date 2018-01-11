package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

import java.io.Serializable;
import java.util.List;

public interface AccountServiceIF extends Serializable {

    List<AbstractAccount> getAccounts(AuthToken authToken) throws AuthException;

    AbstractAccount searchAccount(AuthToken authToken, String alias) throws AuthException;

}
