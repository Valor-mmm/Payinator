package de.othr.has44540.logic.services.user.update;

import de.othr.external.services.oauth.korbinianSchmidt.data.PersonalDataDTO;
import de.othr.has44540.persistance.entities.user.personalData.Address;
import de.othr.has44540.persistance.entities.user.personalData.City;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PersInfoUpdater implements Serializable {

    private static final Logger logger = Logger.getLogger(PersInfoUpdater.class.getName());

    @PersistenceContext
    private EntityManager em;


    public PersonalInformation updatePersInfo(@NotNull PersonalDataDTO newPersonalData, PersonalInformation persInfo) {
        PersonalInformation personalInformation;
        if (persInfo == null) {
            personalInformation = mergePersInfo(newPersonalData, new PersonalInformation());
            personalInformation.setAddress(updateAddress(newPersonalData.getPrimaryAddress(), null));

        } else {
            personalInformation = mergePersInfo(newPersonalData, persInfo);
            personalInformation.setAddress(updateAddress(newPersonalData.getPrimaryAddress(), persInfo.getAddress()));
            personalInformation = em.merge(personalInformation);
        }
        em.persist(personalInformation);
        return personalInformation;
    }

    public Address updateAddress(de.othr.external.services.oauth.korbinianSchmidt.data.Address newAddress,
                                 Address oldAddress) {
        Address address;
        if (oldAddress == null) {
            if (newAddress == null) {
                return null;
            }
            address = mergeAddress(newAddress, new Address());
        } else {
            address = em.merge(mergeAddress(newAddress, oldAddress));
        }
        em.persist(address);
        return address;
    }

    public static PersonalInformation mergePersInfo(PersonalDataDTO newPersonalData,
                                                    @NotNull PersonalInformation personalInformation) {

        if (newPersonalData == null) {
            return personalInformation;
        }
        personalInformation.setLastName(newPersonalData.getLastName());
        personalInformation.setFirstName(newPersonalData.getFirstName());
        System.out.println("Day of birth: [" + newPersonalData.getDayOfBirth() + "]");

        Address newAddress = mergeAddress(newPersonalData.getPrimaryAddress(), personalInformation.getAddress());
        personalInformation.setAddress(newAddress);

        return personalInformation;
    }

    public static Address mergeAddress(de.othr.external.services.oauth.korbinianSchmidt.data.Address newAddress,
                                       @NotNull Address oldAddress) {
        if (newAddress == null) {
            return oldAddress;
        }
        oldAddress.setStreetNumber(newAddress.getStreetNo());
        oldAddress.setStreet(newAddress.getStreet());
        oldAddress.setCountry(newAddress.getCountry());

        City city = new City();
        city.setName(newAddress.getPlace());
        try {
            city.setPostalCode(Integer.parseInt(newAddress.getZipCode()));
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Could not parse postal code to Integer: [" + newAddress.getZipCode() + "]", e);
        }
        oldAddress.setCity(city);

        return oldAddress;
    }

}
