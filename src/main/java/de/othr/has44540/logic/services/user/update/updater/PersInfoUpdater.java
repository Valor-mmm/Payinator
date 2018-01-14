package de.othr.has44540.logic.services.user.update.updater;

import de.othr.external.services.oauth.korbinianSchmidt.data.PersonalDataDTO;
import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterCase;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterQalifier;
import de.othr.has44540.persistance.entities.user.personalData.Address;
import de.othr.has44540.persistance.entities.user.personalData.City;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@EntityUpdaterQalifier(EntityUpdaterCase.PERSONAL_INFO)
public class PersInfoUpdater implements EntityUpdaterIF<PersonalDataDTO, PersonalInformation> {

    private static final Logger logger = Logger.getLogger(PersInfoUpdater.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    @EntityUpdaterQalifier(EntityUpdaterCase.ADDRESS)
    private EntityUpdaterIF<de.othr.external.services.oauth.korbinianSchmidt.data.Address, Address> addressUpdater;


    public PersonalInformation update(@NotNull PersonalDataDTO newPersonalData, PersonalInformation persInfo) throws
                                                                                                              InternalErrorException {
        PersonalInformation personalInformation;
        Address oldAddress = persInfo == null ? null : persInfo.getAddress();
        Address address = addressUpdater.update(newPersonalData.getPrimaryAddress(), oldAddress);
        if (persInfo == null) {
            personalInformation = merge(newPersonalData, new PersonalInformation());
            personalInformation.setAddress(address);
        } else {
            personalInformation = merge(newPersonalData, persInfo);
            personalInformation.setAddress(address);
            personalInformation = em.merge(personalInformation);
        }
        em.persist(personalInformation);
        return personalInformation;
    }

    /**
     * Does not merge address into personal information
     */
    public PersonalInformation merge(PersonalDataDTO newPersonalData,
                                     @NotNull PersonalInformation personalInformation) {

        if (newPersonalData == null) {
            return personalInformation;
        }
        personalInformation.setLastName(newPersonalData.getLastName());
        personalInformation.setFirstName(newPersonalData.getFirstName());
        LocalDate dateOfBirth = LocalDate.now();
        try {
            dateOfBirth = LocalDate.parse(newPersonalData.getDayOfBirth());
        } catch (DateTimeParseException ex) {
            logger.log(Level.WARNING, "Can not parse [" + newPersonalData.getDayOfBirth() + "] to local date.", ex);
        }
        personalInformation.setDateOfBirth(dateOfBirth);

        return personalInformation;
    }

}
