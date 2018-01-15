package de.othr.has44540.logic.services.auth.token;

import de.othr.has44540.logic.services.auth.utils.PasswordGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthToken implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthToken.class.getName());
    private static final PasswordGenerator pwGenerator = new PasswordGenerator(30);
    protected static final long expirationMinutes = 30;

    private UUID id;
    private String key;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationTime;

    public AuthToken() {
        id = UUID.randomUUID();
        creationTime = LocalDateTime.now();
        key = pwGenerator.nextPassword();
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

    public boolean checkToken(AuthToken token) throws IllegalTokenChangeException {
        if (!this.equals(token)) {
            return false;
        }

        String changeErrMessage = "Token with id [" + token.getId() + "] has been changed. Field: ";
        if (!this.getCreationTime().equals(token.getCreationTime())) {
            logger.warning(changeErrMessage + "creationTime");
            throw new IllegalTokenChangeException(token.getId(), "creationTime");
        }
        if (!this.equalsByKey(token)) {
            logger.warning(changeErrMessage + "key");
            throw new IllegalTokenChangeException(token.getId(), "key");
        }
        return true;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
