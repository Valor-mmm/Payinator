package de.othr.has44540.logic.seeder.user.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.paymentInformation.WireTransfer;

public class PaymentMethodDefinition implements SeedDefinition<AbstractPaymentMethod> {

    private boolean isDefault;
    private String name;
    private String accountName;
    private String iban;
    private String bic;

    public PaymentMethodDefinition() {
        this.isDefault = true;
        this.name = "PrimaryKWebshopAccount";
        this.accountName = "Korbi Webshop";
        this.iban = "DE546474937473294";
        this.bic = "BICYLADM";
    }

    @Override
    public AbstractPaymentMethod create() {
        WireTransfer wireTransfer = new WireTransfer();
        wireTransfer.setDefault(isDefault);
        wireTransfer.setName(getName());
        wireTransfer.setAccountName(getAccountName());
        wireTransfer.setIban(getIban());
        wireTransfer.setBic(getBic());
        return wireTransfer;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
}
