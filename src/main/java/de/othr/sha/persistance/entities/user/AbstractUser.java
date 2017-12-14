package de.othr.sha.persistance.entities.user;

import de.othr.sha.persistance.entities.account.impl.DonorAccount;
import de.othr.sha.persistance.entities.user.PaymentInformation.AbstractPaymentMethod;
import de.othr.sha.persistance.entities.user.personalData.Email;
import de.othr.sha.persistance.entities.user.personalData.PersonalInformation;
import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AbstractUser extends GeneratedIDEntity{

    // Attributes

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private Long oauthId;

    @Column(nullable = false)
    private Long personalDataVersion;

    @Column(nullable = false)
    private Long paymentDataVersion;


    // References

    @OneToOne
    @Column(nullable = false)
    private Email email;

    @OneToMany
    @Column(nullable = false)
    private Set<AbstractPaymentMethod> paymentMethods;

    @OneToOne
    @Column(nullable = false)
    private PersonalInformation personalInformation;

    @OneToOne
    private DonorAccount donorAccount;


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

    public Long getPersonalDataVersion() {
        return personalDataVersion;
    }

    public void setPersonalDataVersion(Long personalDataVersion) {
        this.personalDataVersion = personalDataVersion;
    }

    public Long getPaymentDataVersion() {
        return paymentDataVersion;
    }

    public void setPaymentDataVersion(Long paymentDataVersion) {
        this.paymentDataVersion = paymentDataVersion;
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
