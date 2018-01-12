package de.othr.has44540.logic.seeder.user.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.user.personalData.Address;
import de.othr.has44540.persistance.entities.user.personalData.City;

public class AddressDefinition implements SeedDefinition<Address> {

    private String country;
    private String region;
    private String street;
    private String number;

    private String cityName;
    private int postalCode;

    public AddressDefinition() {
        this.country = "Germany";
        this.region = "Bavaria";
        this.street = "Somestreet";
        this.number = "12c";

        this.cityName = "Somecity";
        this.postalCode = 345385;
    }

    @Override
    public Address create() {
        City city = new City();
        city.setPostalCode(getPostalCode());
        city.setName(getCityName());
        Address address = new Address();
        address.setCity(city);
        address.setCountry(getCountry());
        address.setRegion(getRegion());
        address.setStreet(getStreet());
        address.setStreetNumber(getNumber());

        return address;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
