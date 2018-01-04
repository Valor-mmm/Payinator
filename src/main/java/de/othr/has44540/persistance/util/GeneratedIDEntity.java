package de.othr.has44540.persistance.util;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GeneratedIDEntity extends SingleIdEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Override
    public int compareTo(SingleIdEntity<Long> toCompare) {
        if (toCompare == null) {
            throw new NullPointerException("Id to compare is null");
        }

        if (toCompare.getId() == null) {
            throw new NullPointerException("Object toCompare has to have a ID for comparation");
        }

        if (this.getId() == null) {
            throw new NullPointerException("This Object needs to have an id");
        }

        return this.getId().compareTo(toCompare.getId());
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
