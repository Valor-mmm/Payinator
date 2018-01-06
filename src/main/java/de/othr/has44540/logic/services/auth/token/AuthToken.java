package de.othr.has44540.logic.services.auth.token;

import de.othr.has44540.logic.services.auth.utils.PasswordGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AuthToken {

    private static final PasswordGenerator pwGenerator = new PasswordGenerator(30);
    protected static final long expirationMinutes = 30;

    private UUID id;
    private String key;
    private LocalDateTime creationTime;

    public AuthToken() {
        id = UUID.randomUUID();
        creationTime = LocalDateTime.now();
        key = pwGenerator.nextPassword();
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AuthToken))
            return false;
        AuthToken authToken = (AuthToken) o;
        return Objects.equals(id, authToken.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public boolean equalsByKey(AuthToken token) {
        return this.key.equals(token.key);
    }
}
