package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class InternalAccountServiceImpl extends AccountServiceImpl implements InternalAccountSvcIF {

    @Override
    public AccountCase determineAccountCase(AbstractAccount account) throws AuthException {
        for(AccountCase accountCase : AccountCase.values()) {
            if (accountCase.fitsAccout(account)) {
                if (accountCase.equals(AccountCase.ALL)){
                    continue;
                }
                return accountCase;
            }
        }
        return null;
    }
}
