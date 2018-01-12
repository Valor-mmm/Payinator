package de.othr.has44540.logic.seeder.account.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.account.impl.CompanyAccount;

import java.math.BigDecimal;

public class CompanyAccountDefinition implements SeedDefinition<CompanyAccount> {

    private String alias;
    private BigDecimal balance;
    private boolean isDefault;

    public CompanyAccountDefinition() {
        this.alias = "PrimaryKWebshopAccount";
        this.balance = new BigDecimal(900);
        this.isDefault = true;
    }

    public CompanyAccount create() {
        CompanyAccount account = new CompanyAccount();
        account.setAlias(getAlias());
        account.setBalance(getBalance());
        account.setDefault(isDefault());
        return account;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
