package de.othr.has44540.logic.services.user.update.updater;

import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterCase;
import de.othr.has44540.logic.services.user.update.updater.factory.EntityUpdaterQalifier;
import de.othr.has44540.persistance.entities.user.personalData.Address;
import de.othr.has44540.persistance.entities.user.personalData.City;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@EntityUpdaterQalifier(EntityUpdaterCase.ADDRESS)
public class AddressUpdater implements EntityUpdaterIF<de.othr.external.services.oauth.korbinianSchmidt.data.Address, Address> {

    private static final Logger logger = Logger.getLogger(AddressUpdater.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Address update(@NotNull de.othr.external.services.oauth.korbinianSchmidt.data.Address newEntity, Address
            oldEntity) {
        Address address;
        if (oldEntity == null) {
            address = merge(newEntity, new Address());
        } else {
            address = em.merge(merge(newEntity, oldEntity));
        }
        em.persist(address);
        return address;
    }

    @Override
    public Address merge(de.othr.external.services.oauth.korbinianSchmidt.data.Address newEntity,
                         @NotNull Address oldEntity) {
        if (newEntity == null) {
            return oldEntity;
        }
        oldEntity.setStreetNumber(newEntity.getStreetNo());
        oldEntity.setStreet(newEntity.getStreet());
        oldEntity.setCountry(newEntity.getCountry());

        City city = new City();
        city.setName(newEntity.getPlace());
        try {
            city.setPostalCode(Integer.parseInt(newEntity.getZipCode()));
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Could not parse postal code to Integer: [" + newEntity.getZipCode() + "]", e);
        }
        oldEntity.setCity(city);

        return oldEntity;
    }
}
