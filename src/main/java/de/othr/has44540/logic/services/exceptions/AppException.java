package de.othr.has44540.logic.services.exceptions;

import java.io.Serializable;
import java.util.Objects;

public class AppException extends Exception implements Serializable {

    private String title;

    private String description;

    public AppException() {

    }

    public AppException(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AppException))
            return false;
        AppException that = (AppException) o;
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
