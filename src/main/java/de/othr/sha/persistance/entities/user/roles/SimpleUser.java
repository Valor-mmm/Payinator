package de.othr.sha.persistance.entities.user.roles;

import de.othr.sha.persistance.entities.account.impl.SimpleAccount;

import javax.persistence.OneToMany;
import java.util.Set;

public class SimpleUser {

    // Relations

    @OneToMany
    private Set<SimpleAccount> simpleAccounts;

    // Relations -getter/setter


    public Set<SimpleAccount> getSimpleAccounts() {
        return simpleAccounts;
    }

    public void setSimpleAccounts(Set<SimpleAccount> simpleAccounts) {
        this.simpleAccounts = simpleAccounts;
    }
}
