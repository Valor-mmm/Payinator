package de.othr.has44540.persistance.entities.account.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CharityAccount extends SimpleAccount {

    // Attributes

    @Column(nullable = false)
    private String cause = "No Cause";

    private String description;


    // Attributes - getter/setter

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
