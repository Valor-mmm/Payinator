package de.othr.sha.persistance.entities.user.roles;

import de.othr.sha.persistance.entities.account.impl.CharityAccount;
import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Set;

@Entity
public class CharityOrganisation extends GeneratedIDEntity{

    // Attributes

    @Column(unique = true, nullable = false)
    private String name;

    private String nationality;


    // References

    @OneToMany
    private Set<CharityAccount> charityAccounts;


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
