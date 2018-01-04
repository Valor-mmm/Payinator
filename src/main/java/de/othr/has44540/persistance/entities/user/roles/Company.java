package de.othr.has44540.persistance.entities.user.roles;

import de.othr.has44540.persistance.entities.account.impl.CompanyAccount;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

@Entity
public class Company extends GeneratedIDEntity{

    // Attributes

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private BusinessSector sector;


    // References

    @OneToMany(mappedBy = "company")
    private Set<CompanyAccount> companyAccounts;


    // Attributes - getter/setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BusinessSector getSector() {
        return sector;
    }

    public void setSector(BusinessSector sector) {
        this.sector = sector;
    }


    // References - getter/setter

    public Set<CompanyAccount> getCompanyAccounts() {
        return Collections.unmodifiableSet(companyAccounts);
    }

    public void setCompanyAccounts(Set<CompanyAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }
}
