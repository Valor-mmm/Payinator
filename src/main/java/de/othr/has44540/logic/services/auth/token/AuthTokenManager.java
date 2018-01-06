package de.othr.has44540.logic.services.auth.token;

import de.othr.sw.sample.services.AuthTestService;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class AuthTokenManager implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthTokenManager.class.getName());

    private List<AuthToken> authTokens;

    public AuthTokenManager() {
        authTokens = new ArrayList<>();
    }

    public boolean checkToken(AuthToken token) throws IllegalTokenChangeException{
        boolean result = authTokens.contains(token);
        logger.config("Token found: " + result);
        if (result) {
            AuthToken expectedToken = authTokens.get(authTokens.indexOf(authTokens));
            String changeErrMessage = "Token with id [" + token.getId() + "] has been changed. Field: ";
            if (!token.getCreationTime().equals(expectedToken.getCreationTime())) {
                logger.warning(changeErrMessage + "creationTime");
               throw new IllegalTokenChangeException(expectedToken.getId(), "creationTime");
            }
            if (!expectedToken.equalsByKey(token)) {
                logger.warning(changeErrMessage + "key");
                throw new IllegalTokenChangeException(expectedToken.getId(), "key");
            }
        }
        logger.info("Token check. Valid: " + result);
        return result;
    }

    protected void addToken(AuthToken token) {
        authTokens.add(token);
    }

    @Schedule(minute = "*/5", hour = "*", persistent = false)
    private void removeExpiredTokens() {
        logger.info("Removing expired tokens.");
        LocalDateTime now = LocalDateTime.now();
        for (AuthToken token : authTokens) {
            long timeDifference = ChronoUnit.MINUTES.between(token.getCreationTime(), now);
            if (timeDifference < AuthToken.expirationMinutes) {
                continue;
            }
            logger.info("Token with id [" + token.getId().toString() + "] expired.");
            authTokens.remove(token);
        }
        logger.info("Finished removing expired tokens.");
    }
}
