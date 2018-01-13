package de.othr.has44540.logic.services.user.update.updater;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface EntityUpdaterIF <Old,New> extends Serializable{

    New update(@NotNull Old newEntity, New oldEntity);

    New merge(Old newEntity, @NotNull New oldEntity);
}
