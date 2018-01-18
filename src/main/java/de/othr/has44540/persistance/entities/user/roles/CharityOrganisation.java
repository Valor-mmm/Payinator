package de.othr.has44540.persistance.entities.user.roles;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.CharityAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class CharityOrganisation extends AbstractUser {

    // Attributes

    @Column(unique = true, nullable = false)
    private String name;

    private String nationality;


    // References

    @OneToMany
    private Set<CharityAccount> charityAccounts;

    // Methods

    @Override
    public AbstractAccount getDefaultAccount() {
        if (charityAccounts == null) {
            return null;
        }
        for (CharityAccount account : charityAccounts) {
            if (account.getDefault()) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean containsAccount(AbstractAccount account) {
        if (getDonorAccount() != null && getDonorAccount().equals(account)) {
            return true;
        }

        if (charityAccounts == null) {
            return false;
        }
        if (!(account instanceof CharityAccount)) {
            return false;
        }
        return charityAccounts.contains(account);
    }

    // Attributes - getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    // References - getter/setter


    public Set<CharityAccount> getCharityAccounts() {
        return charityAccounts;
    }

    public void setCharityAccounts(Set<CharityAccount> charityAccounts) {
        this.charityAccounts = charityAccounts;
    }
}
