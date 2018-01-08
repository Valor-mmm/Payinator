package de.othr.has44540.logic.services.auth.service.factory;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class AuthServiceMemory implements Serializable {

    private AuthServiceCase serviceCase;

    public AuthServiceCase getServiceCase() {
        return serviceCase;
    }

    public void setServiceCase(AuthServiceCase serviceCase) {
        this.serviceCase = serviceCase;
    }
}
