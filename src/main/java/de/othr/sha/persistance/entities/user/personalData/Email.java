package de.othr.sha.persistance.entities.user.personalData;

import de.othr.sha.persistance.entities.user.AbstractUser;
import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Email extends GeneratedIDEntity {

    // Attributes

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String localPart;


    // References

    @OneToOne()
    @Column(nullable = false)
    private AbstractUser user;


    // Attributes - getter/setter

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLocalPart() {
        return localPart;
    }

    public void setLocalPart(String localPart) {
        this.localPart = localPart;
    }


    // References - getter/setter

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }
}
