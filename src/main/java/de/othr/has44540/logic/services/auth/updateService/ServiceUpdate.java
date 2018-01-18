package de.othr.has44540.logic.services.auth.updateService;

import de.othr.has44540.logic.services.auth.service.AuthServiceIF;

public class ServiceUpdate {

    private AuthServiceIF newService;

    public ServiceUpdate(AuthServiceIF newService) {
        this.newService = newService;
    }

    public AuthServiceIF getNewService() {
        return newService;
    }
}
