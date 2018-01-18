package de.othr.has44540.logic.services.account.accountsvc;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.CharityAccount;
import de.othr.has44540.persistance.entities.account.impl.CompanyAccount;
import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;

public enum  AccountCase {
    ALL("All", AbstractAccount.class, "CadetBlue"),
    DONOR_ACCOUNT("Donor account", DonorAccount.class, "LightGreen"),
    SIMPLE_ACCOUNT("Simple account", SimpleAccount.class, "SteelBlue"),
    COMPANY_ACCOUNT("Company account", CompanyAccount.class, "Orange"),
    CHARITY_ACCOUNT("Charity account", CharityAccount.class, "Violet");

    private String name;
    private Class<? extends AbstractAccount> relatedClass;
    String color;

    AccountCase(String name, Class<? extends AbstractAccount> relatedClass, String color) {
        this.name = name;
        this.relatedClass = relatedClass;
        this.color = color;
    }

    public boolean fitsAccount(AbstractAccount account) {
        if (this.relatedClass.isAssignableFrom(account.getClass())) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends AbstractAccount> getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(Class<? extends AbstractAccount> relatedClass) {
        this.relatedClass = relatedClass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return getName();
    }
}
