package de.othr.sha.persistance.entities.user.PaymentInformation;

import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class WireTransfer extends GeneratedIDEntity{

    // Attributes

    @Column(nullable = false)
    private String accountName;

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private String bic;


    // Attributes -getter/setter

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
