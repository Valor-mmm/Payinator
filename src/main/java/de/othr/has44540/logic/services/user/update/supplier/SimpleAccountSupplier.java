package de.othr.has44540.logic.services.user.update.supplier;

import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.TreeSet;
import java.util.function.Supplier;

public class SimpleAccountSupplier implements Supplier<SimpleAccount> {

    @Inject
    @DefaultAccAliasQualifier
    private Supplier<String> defaultAccAliasSupplier;

    private DonorAccount donorAccount = null;

    public SimpleAccountSupplier() {

    }

    public SimpleAccountSupplier(DonorAccount donorAccount) {
        this.donorAccount = donorAccount;
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
}
