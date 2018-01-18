package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.repositories.AccountRepository;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
public class InternalAccountServiceImpl extends AccountServiceImpl implements InternalAccountSvcIF, UpdatableAuthService{

    @Inject
    private AccountRepository accountRepository;

    @Inject @DetectAutomatically
    private AuthServiceIF authService;

    @Override
    @CheckLogin
    @WebMethod(exclude = true)
    @Transactional
    public List<AbstractAccount> getAccountsByUserAndCase(AccountCase accountCase) throws
                                                                                   AuthException {
        List<AbstractAccount> userAccounts = getAllUserAccounts();
        return userAccounts
                .stream()
                .filter(accountCase::fitsAccount)
                .collect(Collectors.toList());
    }

    @Override
    @CheckLogin
    public List<SimpleAccount> getAllSimpleAccountsForUser() throws AuthException {

        AbstractUser currentUser = authService.getExecutiveUser();
        return getAccountsForCase(AccountCase.SIMPLE_ACCOUNT).stream().filter(currentUser::containsAccount)
                                                             .map(abstractAccount -> (SimpleAccount) abstractAccount)
                                                             .collect(Collectors.toList());
    }

    @CheckLogin
    public List<AbstractAccount> getAllUserAccounts() throws AuthException {

        AbstractUser currentUser = authService.getExecutiveUser();
        return accountRepository.findAll()
                                .stream()
                                .filter(currentUser::containsAccount)
                                .collect(Collectors.toList());
    }

    @CheckLogin
    private List<AbstractAccount> getAccountsForCase(AccountCase accountCase) throws AuthException {
        List<AbstractAccount> allAccounts = accountRepository.findAll();
        return allAccounts.stream().filter(accountCase::fitsAccount).collect(Collectors.toList());
    }

    @Override
    public void updateAuthService(AuthServiceIF newAuthService) {
        this.authService = newAuthService;
    }

    @Override
    public void listenOnUpdateEvent(@Observes ServiceUpdate serviceUpdate) {
        updateAuthService(serviceUpdate.getNewService());
    }
}
