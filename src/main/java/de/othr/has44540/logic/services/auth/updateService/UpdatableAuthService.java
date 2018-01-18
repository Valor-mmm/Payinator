package de.othr.has44540.logic.services.auth.updateService;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;

import javax.enterprise.event.Observes;

public interface UpdatableAuthService {

    void updateAuthService(AuthServiceIF newAuthService);

    void listenOnUpdateEvent(@Observes ServiceUpdate serviceUpdate);
}
