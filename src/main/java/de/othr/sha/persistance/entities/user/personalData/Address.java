package de.othr.sha.persistance.entities.user.personalData;

import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Address extends GeneratedIDEntity{

    // Attributes

    @Column(nullable = false)
    private String country;

    private String region;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;


    // References
    private City city;

    // Attributes -getter/setter

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }


    // References - getter/setter


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
