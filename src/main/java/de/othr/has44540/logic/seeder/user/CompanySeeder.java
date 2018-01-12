package de.othr.has44540.logic.seeder.user;

import de.othr.has44540.logic.seeder.SeedDefinition;
import de.othr.has44540.logic.seeder.account.definitions.CompanyAccountDefinition;
import de.othr.has44540.logic.seeder.user.definitions.*;
import de.othr.has44540.persistance.entities.account.impl.CompanyAccount;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.entities.user.personalData.Address;
import de.othr.has44540.persistance.entities.user.personalData.Email;
import de.othr.has44540.persistance.entities.user.personalData.PersonalInformation;
import de.othr.has44540.persistance.entities.user.roles.Company;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Stateless
public class CompanySeeder {

    @PersistenceContext
    private EntityManager em;

    private CompanyDefinition companyFactory;
    private SeedDefinition<Address> addressFactory;
    private SeedDefinition<CompanyAccount> accountFactory;
    private SeedDefinition<Email> emailFactory;
    private SeedDefinition<AbstractPaymentMethod> paymentMethodFactory;
    private SeedDefinition<PersonalInformation> personalInformationFactory;

    public CompanySeeder() {
        this.addressFactory = new AddressDefinition();
        this.companyFactory = new CompanyDefinition();
        this.accountFactory = new CompanyAccountDefinition();
        this.emailFactory = new EmailDefinition();
        this.paymentMethodFactory = new PaymentMethodDefinition();
        this.personalInformationFactory = new PersonalInformationDefinition();
    }

    /**
     * Check every hour if seeding is necessary
     * If it is: seed
     */
    @Schedule(minute = "*", hour = "*", persistent = false)
    @Transactional
    public void seedCompany() {
        if (isSeedNecessary()) {
            doSeeding();
        }
    }

    private boolean isSeedNecessary() {
        TypedQuery<Company> companyQuery = em
                .createQuery("SELECT c FROM Company AS c WHERE c.name = :companyName", Company.class);
        companyQuery.setParameter("companyName", companyFactory.getName());
        try {
            companyQuery.getSingleResult();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    private void doSeeding() {
        Company company = companyFactory.create();
        CompanyAccount account = accountFactory.create();
        Email email = emailFactory.create();
        AbstractPaymentMethod paymentMethod = paymentMethodFactory.create();
        PersonalInformation personalInformation = personalInformationFactory.create();
        Address address = addressFactory.create();

        account.setCompany(company);
        em.persist(account);
        Set<CompanyAccount> accounts = new TreeSet<>();
        accounts.add(account);
        company.setCompanyAccounts(accounts);


        company.setEmail(email);
        email.setUser(company);

        em.persist(paymentMethod);
        Set<AbstractPaymentMethod> paymentMethods = new TreeSet<>();
        paymentMethods.add(paymentMethod);
        company.setPaymentMethods(paymentMethods);

        personalInformation.setAddress(address);
        company.setPersonalInformation(personalInformation);

        em.persist(address);
        em.persist(personalInformation);
        em.persist(account);
        em.persist(email);
        em.persist(company);
    }
}
