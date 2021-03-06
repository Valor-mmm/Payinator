package de.othr.has44540.persistance.entities.user;

import de.othr.has44540.persistance.entities.account.AbstractAccount;
import de.othr.has44540.persistance.entities.account.impl.DonorAccount;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractUser extends GeneratedIDEntity {

    // Attributes

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private Long oauthId;

    // References

    @XmlTransient
    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private Email email;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @Column(nullable = false)
    private Set<AbstractPaymentMethod> paymentMethods;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private PersonalInformation personalInformation;

    @XmlTransient
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private DonorAccount donorAccount;


    // Methods

    public abstract AbstractAccount getDefaultAccount();

    public AbstractPaymentMethod getDefaultPaymentMethod() {
        for (AbstractPaymentMethod paymentMethod : paymentMethods) {
            if (paymentMethod.getDefault()) {
                return paymentMethod;
            }
        }
        return null;
    }

    public abstract boolean containsAccount(AbstractAccount account);

    // Attributes - getter/setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOauthId() {
        return oauthId;
    }

    public void setOauthId(Long oauthId) {
        this.oauthId = oauthId;
    }

    // References - getter/setter


    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Set<AbstractPaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(Set<AbstractPaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public DonorAccount getDonorAccount() {
        return donorAccount;
    }

    public void setDonorAccount(DonorAccount donorAccount) {
        this.donorAccount = donorAccount;
    }
}
