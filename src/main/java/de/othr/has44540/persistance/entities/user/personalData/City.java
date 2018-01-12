package de.othr.has44540.persistance.entities.user.personalData;

import javax.persistence.Embeddable;

@Embeddable
public class City {

    private String name;
    private int postalCode;

    public String getName() {
        return name;
    }

    public void setName(String city) {
        this.name = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
