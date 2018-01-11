package de.othr.has44540.logic.services.error;

import java.io.Serializable;
import java.util.Objects;

public class ApplicationError implements Serializable {

    private String title;

    private String description;

    public ApplicationError() {

    }

    public ApplicationError(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ApplicationError))
            return false;
        ApplicationError that = (ApplicationError) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
