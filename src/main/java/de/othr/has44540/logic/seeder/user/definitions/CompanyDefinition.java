package de.othr.has44540.logic.seeder.user.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.user.roles.BusinessSector;
import de.othr.has44540.persistance.entities.user.roles.Company;

public class CompanyDefinition implements SeedDefinition<Company> {

    private String username;
    private Long oauthID;
    private String name;
    private BusinessSector sector;
    private Long externalSiteId;

    public CompanyDefinition() {
        this.username = "k4hl";
        this.oauthID = (long) 987652345;
        this.name = "Korbi Webshop";
        this.sector = BusinessSector.SALES;
        this.externalSiteId = new Long(3);
    }

    @Override
    public Company create() {
        Company company = new Company();
        company.setUsername(getUsername());
        company.setOauthId(getOauthID());
        company.setName(getName());
        company.setSector(getSector());
        company.setExternalSiteId(getExternalSiteId());
        return company;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOauthID() {
        return oauthID;
    }

    public void setOauthID(Long oauthID) {
        this.oauthID = oauthID;
    }

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
}
