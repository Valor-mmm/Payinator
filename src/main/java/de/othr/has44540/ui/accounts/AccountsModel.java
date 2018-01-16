package de.othr.has44540.ui.accounts;

import de.othr.has44540.logic.services.account.accountsvc.AccountCase;
import de.othr.has44540.logic.services.account.accountsvc.InternalAccountSvcIF;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifier;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.ui.IndexModel;
import de.othr.has44540.ui.utils.AuthModel;
import de.othr.has44540.ui.utils.ErrorModel;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class AccountsModel implements Serializable {

    private static final Logger logger = Logger.getLogger(AccountsModel.class.getName());

    public static final String pageName = "accounts";

    @Inject
    private InternalAccountSvcIF accountService;

    @Inject
    @AuthServiceQualifier(AuthServiceCase.SESSION_BASED)
    private AuthServiceIF authService;

    @Inject
    private ErrorModel errorModel;

    @Inject
    private AuthModel authModel;

    private List<AbstractAccount> accounts;

    private AccountCase accountCase = AccountCase.ALL;

    public void retrieveAccounts() {
        try {
            logger.info("Retrieving accounts.");
            accounts = accountService.getAccounts(null);
            return;
        } catch (AuthException e) {
            handleAuthError(e);
        } catch (Exception e) {
            handleUnknownError(e);
        }
    }

    public List<AbstractAccount> getAccounts() {
        retrieveAccounts();
        return accounts;
    }

    public AccountCase getAccountCase() {
        return accountCase;
    }

    public String getColorForAccount(AbstractAccount account) {
        AccountCase accountCase = getAccountCaseForAccount(account);
        if (accountCase == null) {
            logger.warning("Account case was null.");
            return "";
        }
        return accountCase.getColor();
    }

    public String getCaseNameForAccount(AbstractAccount account) {
        AccountCase accountCase = getAccountCaseForAccount(account);
        if (accountCase == null) {
            logger.warning("Account case was null.");
            return "";
        }
        return accountCase.getName();
    }

    private AccountCase getAccountCaseForAccount(AbstractAccount account) {
        try {
            return accountService.determineAccountCase(account);
        } catch (AuthException e) {
            handleAuthError(e);
        } catch (Exception e) {
            handleUnknownError(e);
        }
        logger.severe("Account case is null.");
        return null;
    }

    public String delete(AbstractAccount account) {
        return pageName;
    }

    public String getBalance(AbstractAccount account) {
        if (account instanceof SimpleAccount) {
            BigDecimal balance =((SimpleAccount) account).getBalance();
            if (balance != null) {
                return balance.toString() + "€";
            }
        }
        return "-";
    }

    public List<AccountCase> getAccountCaseList() {
        return Arrays.asList(AccountCase.values());
    }

    public void selectionChanged(AjaxBehaviorEvent event) {
      logger.info("Selection changed. To [" + this.accountCase + "]");
      retrieveAccounts();
    }

    private void handleAuthError(AuthException e) {
        logger.log(Level.WARNING, "Auth exception occurred during account service communication.", e);
        authModel.requireLogin(pageName);
        IndexModel.redirectToPage(AuthModel.pageName);
    }

    private void handleUnknownError(Exception e) {
        logger.log(Level.SEVERE, "Unexpected error during accountService communication.", e);
        errorModel.setUnknownError();
        ErrorModel.redirectToErrorPage();
    }
}
