package de.othr.has44540.persistance.repositories;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.util.SingleIdEntityRepository;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SessionScoped
public class PaymentMethodRepository extends SingleIdEntityRepository<Long, AbstractPaymentMethod> implements Serializable, UpdatableAuthService {

    private static final Logger logger = Logger.getLogger(PaymentMethodRepository.class.getName());

    @Inject
    @DetectAutomatically
    private AuthServiceIF authService;

    @PersistenceContext(unitName = SingleIdEntityRepository.PERSISTANCE_UNIT_NAME)
    private EntityManager em;

    public PaymentMethodRepository() {
        super(AbstractPaymentMethod.class);
    }

    public List<AbstractPaymentMethod> findByName(String name) {
        if (name == null) {
            return null;
        }

        TypedQuery<AbstractPaymentMethod> byNameQuery = em
                .createQuery("SELECT pm FROM AbstractPaymentMethod AS pm WHERE pm.name = :name",
                             AbstractPaymentMethod.class).setParameter("name", name);
        return byNameQuery.getResultList();
    }

    @CheckLogin
    public AbstractPaymentMethod findByNameInUser(AuthToken authToken, String name) throws AuthException {
        AbstractUser user = authService.getExecutiveUser();
        if (name == null || user == null || user.getPaymentMethods() == null) {
            return null;
        }

        List<AbstractPaymentMethod> foundMethods = user.getPaymentMethods().stream()
                                                       .filter(payment -> name.equals(payment.getName()))
                                                       .collect(Collectors.toList());
        if (foundMethods.size() > 1) {
            logger.warning("Query for payment method by name at user returned more than one finds: " + foundMethods.size());
        }
        if (foundMethods.size() < 1) {
            logger.warning("Could not found a PaymentMethod for the user with name: " + name);
            return null;
        }
        return foundMethods.get(0);

    }

    @Override
    public void updateAuthService(AuthServiceIF newAuthService) {
        this.authService = newAuthService;
    }

    @Override
    public void listenOnUpdateEvent(@Observes ServiceUpdate serviceUpdate) {
        updateAuthService(serviceUpdate.getNewService());
    }
}
