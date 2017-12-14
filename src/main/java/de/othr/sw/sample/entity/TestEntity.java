package de.othr.sw.sample.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String stringProperty;
    private int integerProperty;

    public final long getId() {
        return id;
    }

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public int getIntegerProperty() {
        return integerProperty;
    }

    public void setIntegerProperty(int integerProperty) {
        this.integerProperty = integerProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity entity = (TestEntity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
