package de.othr.has44540.logic.services.error;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public interface ErrorServiceIF extends Serializable {

    void addError(@NotNull ApplicationError e);

    void addError(@NotNull String title, @NotNull String description);

    ApplicationError getMostRecent();
}
