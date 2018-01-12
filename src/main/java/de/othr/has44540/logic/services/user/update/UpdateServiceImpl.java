package de.othr.has44540.logic.services.user.update;

import de.othr.has44540.persistance.entities.user.AbstractUser;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateServiceImpl implements UpdateServiceIF {

    @Override
    public AbstractUser updateUser(@NotNull String sessionToken, AbstractUser user) {
        return null;
    }

    @Override
    public AbstractUser updateCompany(@NotNull Long externalSiteId) {
        return null;
    }
}
