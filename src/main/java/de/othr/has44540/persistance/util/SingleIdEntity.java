package de.othr.has44540.persistance.util;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SingleIdEntity <T> implements Comparable<SingleIdEntity<T>>{

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleIdEntity<?> that = (SingleIdEntity<?>) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{ id=" + getId() + " }";
    }

    @Override
    public abstract int compareTo(SingleIdEntity<T> toCompare);
}
