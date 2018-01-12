package de.othr.has44540.logic.seeder.user.definitions;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;

import java.time.LocalDate;

public class PersonalInformationDefinition implements SeedDefinition<PersonalInformation> {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public PersonalInformationDefinition() {
        this.firstName = "Korbi";
        this.lastName = "4Hl";
        this.dateOfBirth = LocalDate.of(1996, 11, 4);
    }

    @Override
    public PersonalInformation create() {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setDateOfBirth(getDateOfBirth());
        personalInformation.setFirstName(getFirstName());
        personalInformation.setLastName(getLastName());
        return personalInformation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
