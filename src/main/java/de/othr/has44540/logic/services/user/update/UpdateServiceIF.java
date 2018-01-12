package de.othr.has44540.logic.services.user.update;

import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface UpdateServiceIF extends Serializable {

    AbstractUser updateUser(@NotNull String sessionToken, AbstractUser user);

    AbstractUser updateCompany(@NotNull Long externalSiteId);

}
