package de.othr.has44540.logic.services;

import de.othr.has44540.persistance.entities.account.AbstractAccount;

public class InvalidAccountException extends RuntimeException {

    private Long primaryKey;
    private AbstractAccount account;

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(Long primaryKey) {
        super();
        this.primaryKey = primaryKey;
    }

    public InvalidAccountException(AbstractAccount account) {
        super();
        if (account == null) {
            this.primaryKey = (long) -1;
        } else {
            this.primaryKey = account.getId();
        }
        this.account = account;
    }

    public InvalidAccountException(String message, Long primaryKey) {
        this(message);
        this.primaryKey = primaryKey;
    }

    public InvalidAccountException(String message, AbstractAccount account) {
        this(message);
        if (account == null) {
            this.primaryKey = (long) -1;
        } else {
            this.primaryKey = account.getId();
        }
        this.account = account;
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }

    public AbstractAccount getAccount() {
        return account;
    }

    public void setAccount(AbstractAccount account) {
        this.account = account;
    }
}
