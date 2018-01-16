package de.othr.has44540.persistance.entities.account;

import de.othr.has44540.logic.services.auth.token.LocalDateTimeAdapter;
import de.othr.has44540.persistance.entities.account.impl.SimpleAccount;
import de.othr.has44540.persistance.entities.user.paymentInformation.AbstractPaymentMethod;
import de.othr.has44540.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Payment extends GeneratedIDEntity {

    // Attributes

    @Column(nullable = false)
    private BigDecimal amount;

    private String cause = null;

    private String message = null;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationTime;


    // References

    @ManyToOne
    private AbstractAccount fromAccount;

    @ManyToOne
    private SimpleAccount toAccount;

    @ManyToOne
    private AbstractPaymentMethod paymentMethod;

    public Payment() {
        this.creationTime = LocalDateTime.now();
    }

    public Payment(AbstractAccount fromAccount, AbstractPaymentMethod paymentMethod) {
        this();
        this.fromAccount = fromAccount;
        this.paymentMethod = paymentMethod;
    }


    // Attributes - getter/setter

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    // References - getter/setter


    public AbstractAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(AbstractAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public SimpleAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(SimpleAccount toAccount) {
        this.toAccount = toAccount;
    }

    public AbstractPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(AbstractPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
