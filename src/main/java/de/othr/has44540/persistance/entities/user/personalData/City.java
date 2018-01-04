package de.othr.has44540.persistance.entities.user.personalData;

import javax.persistence.Embeddable;

@Embeddable
public class City {

    private String city;
    private int postalCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
