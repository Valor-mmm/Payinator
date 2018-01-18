package de.othr.has44540.persistance.entities.user.paymentInformation;

import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlSeeAlso({WireTransfer.class, CreditCard.class})
public abstract class AbstractPaymentMethod extends GeneratedIDEntity {

    // Attributes

    private Long oauthID;

    private Boolean isDefault;

    private String name;


    // Methods

    @Override
    public String toString() {
        return name;
    }


    // Attributes -getter/setter

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOauthID() {
        return oauthID;
    }

    public void setOauthID(Long oauthID) {
        this.oauthID = oauthID;
    }
}
