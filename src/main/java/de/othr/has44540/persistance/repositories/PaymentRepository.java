package de.othr.has44540.persistance.repositories;

import de.othr.has44540.persistance.entities.account.Payment;
import de.othr.has44540.persistance.util.SingleIdEntityRepository;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class PaymentRepository extends SingleIdEntityRepository<Long, Payment> implements Serializable{

    public PaymentRepository() {
        super(Payment.class);
    }
}
