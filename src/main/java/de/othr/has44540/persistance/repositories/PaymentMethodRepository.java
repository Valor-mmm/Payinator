package de.othr.has44540.persistance.repositories;

import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.util.SingleIdEntityRepository;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;

@SessionScoped
public class PaymentMethodRepository extends SingleIdEntityRepository<Long, AbstractPaymentMethod> implements Serializable {

    @PersistenceContext(unitName = SingleIdEntityRepository.PERSISTANCE_UNIT_NAME)
    private EntityManager em;

    public PaymentMethodRepository() {
        super(AbstractPaymentMethod.class);
    }

    public AbstractPaymentMethod findByName(String name) {
        if (name == null) {
            return null;
        }

        TypedQuery<AbstractPaymentMethod> byNameQuery = em
                .createQuery("SELECT pm FROM AbstractPaymentMethod AS pm WHERE pm.name = :name",
                             AbstractPaymentMethod.class).setParameter("name", name);
        try {
            return byNameQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
