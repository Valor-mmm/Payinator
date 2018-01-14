package de.othr.has44540.logic.services.user.update;

import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import de.othr.has44540.logic.services.exceptions.OAuthException;
import de.othr.has44540.persistance.entities.user.roles.Company;
import de.othr.has44540.persistance.entities.user.roles.SimpleUser;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface UpdateServiceIF extends Serializable {

    SimpleUser updateUser(@NotNull String sessionToken, SimpleUser user) throws OAuthException, InternalErrorException;

    Company updateCompany(@NotNull Long externalSiteId);

}
