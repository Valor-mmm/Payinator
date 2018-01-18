package de.othr.has44540.logic.services.account.payment;

import de.othr.has44540.logic.services.account.accountsvc.InternalAccountSvcIF;
import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
public class InternalPaymentServiceImpl extends PaymentService implements InternalPaymentSvcIF, UpdatableAuthService {

    @Inject
    private InternalAccountSvcIF accountService;

    @Inject
    @DetectAutomatically
    private AuthServiceIF authService;

    @CheckLogin
    @Override
    public List<Payment> getIncomingForUser() throws AuthException {
        return accountService.getAllSimpleAccountsForUser().stream().map(SimpleAccount::getPaymentsIn)
                             .flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    @CheckLogin
    public List<Payment> getOutgoingForUser() throws AuthException {
        return accountService.getAllSimpleAccountsForUser().stream().map(SimpleAccount::getPaymentsOut)
                             .flatMap(Collection::stream).collect(Collectors.toList());
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
