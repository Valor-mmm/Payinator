package de.othr.has44540.persistance.entities.account.impl;

import de.othr.has44540.persistance.entities.user.roles.Company;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CompanyAccount extends SimpleAccount {

    // References

    @ManyToOne
    private Company company;


    // References - getter/setter


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}