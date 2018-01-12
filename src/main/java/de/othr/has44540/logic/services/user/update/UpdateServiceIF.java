package de.othr.has44540.logic.services.user.update;

import de.othr.has44540.logic.services.exceptions.auth.AuthException;
import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface UpdateServiceIF extends Serializable {

    boolean updateUser(@NotNull String sessionToken, AbstractUser user) throws AuthException;

    boolean createUser(@NotNull String sessionToken) throws AuthException;
}
