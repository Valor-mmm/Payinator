package de.othr.sha.persistance.entities.account;

import de.othr.sha.persistance.entities.account.impl.SimpleAccount;
import de.othr.sha.persistance.util.GeneratedIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Payment extends GeneratedIDEntity {

    // Attributes

    @Column(nullable = false)
    private BigDecimal amount;

    private String cause;

    private String message;


    // References

    @ManyToOne
    private AbstractAccount fromAccount;

    @ManyToOne
    private SimpleAccount toAccount;


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
}
