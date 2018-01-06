package de.othr.has44540.persistance.entities.user.paymentInformation;

import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Entity;

@Entity
public class AbstractPaymentMethod extends GeneratedIDEntity {

    // Attributes

    private Boolean isDefault;

    private String name;


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
}