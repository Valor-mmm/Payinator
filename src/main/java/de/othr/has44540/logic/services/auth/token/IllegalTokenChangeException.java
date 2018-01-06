package de.othr.has44540.logic.services.auth.token;

import java.util.UUID;

public class IllegalTokenChangeException extends Exception {

    private UUID tokenId;
    private String changedFieldName;

    public IllegalTokenChangeException(UUID tokenId, String changedFieldName) {
        this.tokenId = tokenId;
        this.changedFieldName = changedFieldName;
    }


    public UUID getTokenId() {
        return tokenId;
    }

    public String getChangedFieldName() {
        return changedFieldName;
    }
}
