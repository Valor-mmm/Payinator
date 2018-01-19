package de.othr.has44540.ui.accounts;

import de.othr.has44540.logic.services.account.accountsvc.InternalAccountSvcIF;
import de.othr.has44540.logic.services.account.payment.InternalPaymentSvcIF;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceCase;
import de.othr.has44540.logic.services.auth.service.factory.AuthServiceQualifier;
import de.othr.has44540.logic.services.exceptions.account.AccountException;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.ui.IndexModel;
import de.othr.has44540.ui.utils.AuthModel;
import de.othr.has44540.ui.utils.ErrorModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class PaymentModel implements Serializable {

    public static final Logger logger = Logger.getLogger(PaymentModel.class.getName());

    public static final String pageName = "payment";

    @Inject
    private InternalAccountSvcIF accountService;

    @Inject
    private InternalPaymentSvcIF paymentService;

    @Inject
    @AuthServiceQualifier(AuthServiceCase.SESSION_BASED)
    private AuthServiceIF authService;

    @Inject
    private ErrorModel errorModel;

    @Inject
    private AuthModel authModel;

    @Inject
    private AccountConverter accountConverter;

    @Inject
    private SimpleAccountConverter simpleAccountConverter;

    @Inject
    private PaymentMethodConverter paymentMethodConverter;

    /*
        Block used for creation of new payment
     */
    private Double amount;
    private SimpleAccount toAccount;
    private SimpleAccount fromAccount; // Exclude donation accounts which should be used for donations only
    private List<SimpleAccount> allFromAccounts;
    private AbstractPaymentMethod paymentMethod;
    private Set<AbstractPaymentMethod> allPaymentMethods;
    private String cause;
    private String message;

    private List<Payment> paymentsIn;
    private List<Payment> paymentsOut;

    @PostConstruct
    public void initPaymentModel() {
        initPaymentInOutLists();
        initPaymentMethodSet();
        initFromAccountList();
    }

    private void initPaymentInOutLists() {
        try {
            paymentsIn = paymentService.getIncomingForUser();
            paymentsOut = paymentService.getOutgoingForUser();
        } catch (AuthException e) {
            handleAuthError(e);
        }
    }

    private void initPaymentMethodSet() {
        AbstractUser user = authService.getLoggedInUser();
        if (user == null) {
            allPaymentMethods = new HashSet<>();
        }

        allPaymentMethods = authService.getLoggedInUser().getPaymentMethods();
        if (allPaymentMethods != null && !allPaymentMethods.isEmpty()) {
            paymentMethod = allPaymentMethods.iterator().next();
        }
    }

    private void initFromAccountList() {
        try {
            allFromAccounts = accountService.getAllSimpleAccountsForUser();
            if (!allFromAccounts.isEmpty()) {
                AbstractUser loggedInUser = authService.getLoggedInUser();
                if (loggedInUser == null) {
                    fromAccount = allFromAccounts.get(0);
                    return;
                }

                AbstractAccount defaultAccount = loggedInUser.getDefaultAccount();
                if (defaultAccount == null || !(defaultAccount instanceof SimpleAccount)) {
                    fromAccount = allFromAccounts.get(0);
                    return;

                }
                fromAccount = (SimpleAccount) defaultAccount;
            }
        } catch (AuthException e) {
            handleAuthError(e);
        }
    }

    public List<Payment> retrievePaymentsOut() {
        initPaymentInOutLists();
        return paymentsOut;
    }

    private void checkPayment() {
        String errTitle = "Wrong or missing inputs.";
        if (amount == null) {
            errorModel.setError(errTitle, "Please enter a decimal number for the Amount field. Example: \"40.6\"");
            IndexModel.redirectToPage(ErrorModel.pageName);
        }
        if (toAccount == null) {
            errorModel.setError(errTitle,
                                "The account you entered was not valid. Please enter a valid account alias.");
            IndexModel.redirectToPage(ErrorModel.pageName);
        }
        if (fromAccount == null || paymentMethod == null) {
            errorModel.setUnknownError();
            IndexModel.redirectToPage(ErrorModel.pageName);
        }
    }

    // To create a new payment
    public String create() {
        checkPayment();
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal(amount));
        payment.setToAccount(toAccount);
        payment.setFromAccount(fromAccount);
        payment.setCause(cause);
        payment.setMessage(message);
        payment.setPaymentMethod(paymentMethod);

        try {
            paymentService.makePayment(null, payment);
        } catch (AuthException e) {
            handleAuthError(e);
        } catch (AccountException e) {
            logger.log(Level.SEVERE, "Account error during communication", e);
            errorModel.setError(e.getTitle(), e.getDescription());
            IndexModel.redirectToPage(ErrorModel.pageName);
        }

        return pageName;
    }

    private void handleAuthError(AuthException e) {
        logger.log(Level.WARNING, "Auth exception during service communication.", e);
        authModel.requireLogin(pageName);
        IndexModel.redirectToPage(AuthModel.pageName);
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public SimpleAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(SimpleAccount toAccount) {
        this.toAccount = toAccount;
    }

    public SimpleAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(SimpleAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public List<SimpleAccount> getAllFromAccounts() {
        return allFromAccounts;
    }

    public void setAllFromAccounts(List<SimpleAccount> allFromAccounts) {
        this.allFromAccounts = allFromAccounts;
    }

    public AbstractPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(AbstractPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<AbstractPaymentMethod> getAllPaymentMethods() {
        return allPaymentMethods;
    }

    public void setAllPaymentMethods(Set<AbstractPaymentMethod> allPaymentMethods) {
        this.allPaymentMethods = allPaymentMethods;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Payment> getPaymentsIn() {
        return paymentsIn;
    }

    public void setPaymentsIn(List<Payment> paymentsIn) {
        this.paymentsIn = paymentsIn;
    }

    public List<Payment> getPaymentsOut() {
        return paymentsOut;
    }

    public void setPaymentsOut(List<Payment> paymentsOut) {
        this.paymentsOut = paymentsOut;
    }

    public AccountConverter getAccountConverter() {
        return accountConverter;
    }

    public PaymentMethodConverter getPaymentMethodConverter() {
        return paymentMethodConverter;
    }

    public SimpleAccountConverter getSimpleAccountConverter() {
        return simpleAccountConverter;
    }
}
