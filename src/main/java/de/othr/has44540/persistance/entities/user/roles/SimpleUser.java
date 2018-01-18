package de.othr.has44540.persistance.entities.user.roles;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class SimpleUser extends AbstractUser {

    // Relations

    @OneToMany(fetch = FetchType.EAGER)
    private Set<SimpleAccount> simpleAccounts;

    // Methods

    @Override
    public AbstractAccount getDefaultAccount() {
        if (simpleAccounts == null) {
            return null;
        }
        for (SimpleAccount account : simpleAccounts) {
            if (account.getDefault()) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean containsAccount(AbstractAccount account) {
        if (getDonorAccount()!= null && getDonorAccount().equals(account)) {
            return true;
        }

        if (simpleAccounts == null) {
            return false;
        }
        return account instanceof SimpleAccount && simpleAccounts.contains(account);

    }

    // Relations -getter/setter


    public Set<SimpleAccount> getSimpleAccounts() {
        return simpleAccounts;
    }

    public void setSimpleAccounts(Set<SimpleAccount> simpleAccounts) {
        this.simpleAccounts = simpleAccounts;
    }
}
