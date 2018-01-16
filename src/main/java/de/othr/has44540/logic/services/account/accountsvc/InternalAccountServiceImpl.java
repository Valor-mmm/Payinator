package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;

import javax.enterprise.context.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@SessionScoped
public class InternalAccountServiceImpl extends AccountServiceImpl implements InternalAccountSvcIF {

    private static final Logger logger = Logger.getLogger(InternalAccountServiceImpl.class.getName());

    @Override
    @CheckLogin
    public AccountCase determineAccountCase(AbstractAccount account) throws AuthException {
        for (AccountCase accountCase : AccountCase.values()) {
            if (accountCase.fitsAccout(account)) {
                if (accountCase.equals(AccountCase.ALL)) {
                    continue;
                }
                return accountCase;
            }
        }
        return null;
    }

    @Override
    @CheckLogin
    public List<AbstractAccount> getAccountsByCase(AccountCase accountCase) throws
                                                                            AuthException,
                                                                            InternalErrorException {
        List<AbstractAccount> result = new ArrayList<>();
        List<AbstractAccount> allAccounts = this.getAccounts(null);
        if (allAccounts == null) {
            logger.severe("Getting all accounts returns null");
            throw new InternalErrorException("Error during retrieving of all accounts.",
                                             "The list of all accounts is null");
        }

        for (AbstractAccount account: allAccounts) {
            if (accountCase.fitsAccout(account)) {
                result.add(account);
            }
        }
        return result;
    }
}
