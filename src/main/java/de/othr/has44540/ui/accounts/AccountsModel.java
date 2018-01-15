package de.othr.has44540.ui.accounts;

import de.othr.has44540.logic.services.account.accountsvc.AccountServiceIF;
import de.othr.has44540.logic.services.account.accountsvc.InternalAccountSvcIF;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AccountsModel implements Serializable {

    @Inject
    private InternalAccountSvcIF accountService;


}
