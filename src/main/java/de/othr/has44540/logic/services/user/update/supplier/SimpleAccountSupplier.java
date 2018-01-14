package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.TreeSet;
import java.util.function.Supplier;

@RequestScoped
@EntitySupplierQualifier(EntitySupplierCase.SIMPLE_ACCOUNT)
public class SimpleAccountSupplier implements Supplier<SimpleAccount>, Serializable {

    @Inject
    @EntitySupplierQualifier(EntitySupplierCase.DEFAULT_ACC_ALIAS)
    private Supplier<String> defaultAccAliasSupplier;

    private DonorAccount donorAccount;

    public SimpleAccountSupplier() {
        donorAccount = null;
    }

    @Override
    public SimpleAccount get() {
        SimpleAccount simpleAccount = new SimpleAccount();
        simpleAccount.setBalance(new BigDecimal(0));
        simpleAccount.setDefault(true);
        simpleAccount.setDonorAccount(donorAccount);
        simpleAccount.setAlias(defaultAccAliasSupplier.get());
        simpleAccount.setPaymentsIn(new TreeSet<>());
        simpleAccount.setPaymentsOut(new TreeSet<>());
        return simpleAccount;
    }

    public void setDonorAccount(DonorAccount donorAccount) {
        this.donorAccount = donorAccount;
    }

    public DonorAccount getDonorAccount() {
        return donorAccount;
    }
}
