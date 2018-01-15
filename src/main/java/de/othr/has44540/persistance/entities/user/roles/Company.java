package de.othr.has44540.persistance.entities.user.roles;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.CompanyAccount;
import de.othr.has44540.persistance.entities.user.AbstractUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.Set;

@Entity
public class Company extends AbstractUser {

    // Attributes

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private BusinessSector sector;

    @Column(unique = true)
    private Long externalSiteId;

    // References

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<CompanyAccount> companyAccounts;

    // Methods

    @Override
    public AbstractAccount getDefaultAccount() {
        if (companyAccounts == null) {
            return null;
        }
        for (CompanyAccount account : companyAccounts) {
            if (account.getDefault()) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean containsAccount(AbstractAccount account) {
        if (companyAccounts == null) {
            return false;
        }
        return account instanceof CompanyAccount && companyAccounts.contains(account);
    }

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

    public Long getExternalSiteId() {
        return externalSiteId;
    }

    public void setExternalSiteId(Long externalSiteId) {
        this.externalSiteId = externalSiteId;
    }

    // References - getter/setter

    public Set<CompanyAccount> getCompanyAccounts() {
        return Collections.unmodifiableSet(companyAccounts);
    }

    public void setCompanyAccounts(Set<CompanyAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }
}
