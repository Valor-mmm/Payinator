package de.othr.has44540.logic.services;

import de.othr.has44540.persistance.entities.account.AbstractAccount;

public class UnknownAccountException extends RuntimeException {

    private Long primaryKey;

    public UnknownAccountException(Long primaryKey) {
        super();
        this.primaryKey = primaryKey;
    }

    public UnknownAccountException(AbstractAccount account) {
        super();
        this.primaryKey = account.getId();
    }

    public UnknownAccountException(String message, Long primaryKey) {
        super(message);
        this.primaryKey = primaryKey;
    }

    public UnknownAccountException(String message, AbstractAccount account) {
        super(message);
        this.primaryKey = account.getId();
    }

    public Long getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Long primaryKey) {
        this.primaryKey = primaryKey;
    }
}
