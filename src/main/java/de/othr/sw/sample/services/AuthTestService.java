package de.othr.sw.sample.services;

import javax.enterprise.context.SessionScoped;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.Serializable;

@WebService
@SessionScoped
public class AuthTestService implements Serializable {

    private String auth;

    @WebMethod
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }
}
