package de.othr.has44540.logic.services.user.update;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateServiceImpl implements UpdateServiceIF {

    @Override
    public boolean updateUser(@NotNull String sessionToken, AbstractUser user) throws AuthException {
        return false;
    }

    @Override
    public boolean createUser(@NotNull String sessionToken) throws AuthException {
        return false;
    }
}
