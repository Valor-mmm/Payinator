package de.othr.has44540.logic.seeder.user.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.user.personalData.Email;

public class EmailDefinition implements SeedDefinition<Email> {

    private String localPart;
    private String domain;

    public EmailDefinition() {
        this.localPart = "korbi";
        this.domain = "4hl.com";
    }

    @Override
    public Email create() {
        Email email = new Email();
        email.setDomain(getDomain());
        email.setLocalPart(getLocalPart());
        return email;
    }

    public String getLocalPart() {
        return localPart;
    }

    public void setLocalPart(String localPart) {
        this.localPart = localPart;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
