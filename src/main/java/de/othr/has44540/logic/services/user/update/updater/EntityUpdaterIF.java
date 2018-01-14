package de.othr.has44540.logic.services.user.update.updater;

import de.othr.has44540.logic.services.exceptions.InternalErrorException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface EntityUpdaterIF <Old,New> extends Serializable{

    New update(@NotNull Old newEntity, New oldEntity) throws InternalErrorException;

    New merge(Old newEntity, @NotNull New oldEntity) throws InternalErrorException;
}
