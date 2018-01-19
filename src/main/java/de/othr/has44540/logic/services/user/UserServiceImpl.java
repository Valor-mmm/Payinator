package de.othr.has44540.logic.services.user;

import de.othr.has44540.logic.services.auth.LoginInterceptor.CheckLogin;
import de.othr.has44540.logic.services.auth.service.AuthServiceIF;
import de.othr.has44540.logic.services.auth.service.factory.DetectAutomatically;
import de.othr.has44540.logic.services.auth.token.AuthToken;
import de.othr.has44540.logic.services.auth.updateService.ServiceUpdate;
import de.othr.has44540.logic.services.auth.updateService.UpdatableAuthService;
import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import sun.rmi.runtime.Log;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@SessionScoped
public class UserServiceImpl implements UserServiceIF, UpdatableAuthService {

    @Inject @DetectAutomatically
    private AuthServiceIF authService;

    public static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CheckLogin
    public AbstractUser deleteUser(AuthToken authToken) throws AuthException {
        AbstractUser user = authService.getLoggedInUser();
        logger.info("Removing user: [" + user.getUsername() +"]");
        AbstractUser mergedUser = em.merge(user);
        em.remove(mergedUser);
        return mergedUser;
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
