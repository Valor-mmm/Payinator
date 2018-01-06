package de.othr.has44540.logic.services.auth;

import de.othr.has44540.logic.services.auth.AuthServiceIF;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.entities.user.personalData.Email;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class AuthServiceImpl implements AuthServiceIF, Serializable{

    private AbstractUser loggedInUser;

    @Override
    public AbstractUser getLoggedInUser() {
        return null;
    }

    public void setLoggedInUser(AbstractUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
