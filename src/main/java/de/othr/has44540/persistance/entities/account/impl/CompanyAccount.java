package de.othr.has44540.persistance.entities.account.impl;

import de.othr.has44540.persistance.entities.user.roles.Company;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyAccount extends SimpleAccount {

    // References

    @XmlTransient
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
