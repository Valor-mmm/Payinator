package de.othr.has44540.persistance.entities.user.personalData;

import de.othr.has44540.persistance.entities.user.AbstractUser;
import de.othr.has44540.persistance.util.GeneratedIDEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Email extends GeneratedIDEntity {

    private static transient final String atSymbol = "@";

    // Attributes

    @Column(nullable = false)
    private String domain;

    @Column(nullable = false)
    private String localPart;


    // References

    @OneToOne()
    private AbstractUser user;


    @NotNull
    @Contract(pure = true)
    public static String getEmailString(String localPart, String domain) {
        return localPart + atSymbol + domain;
    }

    @NotNull
    public static String getLocalPart(@NotNull String email) {
        int atIndex = email.indexOf(atSymbol);
        int atLastIndex = email.lastIndexOf(atSymbol);
        if (atIndex == -1 || atIndex != atLastIndex) {
            throw new IllegalArgumentException("Email String is not valid as it contains no @ or more than one.");
        }
        return email.substring(0, atIndex);
    }

    @NotNull
    public static String getDomain(@NotNull String email) {
        int atIndex = email.indexOf(atSymbol);
        int atLastIndex = email.lastIndexOf(atSymbol);
        if (atIndex == -1 || atIndex != atLastIndex) {
            throw new IllegalArgumentException("Email String is not valid as it contains no @ or more than one.");
        }
        return email.substring(atIndex + 1, email.length());
    }

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
