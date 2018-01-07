package de.othr.has44540.logic.services.auth.token;

import de.othr.has44540.logic.services.auth.UserSession;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

@Stateless
public class AuthTokenManager implements Serializable {

    private static final Logger logger = Logger.getLogger(AuthTokenManager.class.getName());

    private Map<AuthToken, UserSession> tokenUserMap;

    public AuthTokenManager() {
        tokenUserMap = new TreeMap<>();
    }

    public boolean checkToken(AuthToken token) throws IllegalTokenChangeException{
        boolean result = tokenUserMap.containsKey(token);
        logger.config("Token found: " + result);
        if (result) {
            Set<AuthToken> tokenSet = tokenUserMap.keySet();
            for (AuthToken expectedToken : tokenSet) {
                expectedToken.checkToken(token);
            }
        }
        logger.info("Token check. Valid: " + result);
        return result;
    }

    protected void addToken(AuthToken token, UserSession userSession) {
        tokenUserMap.put(token, userSession);
    }

    @Schedule(minute = "*/5", hour = "*", persistent = false)
    private void removeExpiredTokens() {
        logger.info("Removing expired tokens.");
        LocalDateTime now = LocalDateTime.now();
        Set<AuthToken> tokenSet = tokenUserMap.keySet();
        for (AuthToken token : tokenSet) {
            long timeDifference = ChronoUnit.MINUTES.between(token.getCreationTime(), now);
            if (timeDifference < AuthToken.expirationMinutes) {
                continue;
            }
            logger.info("Token with id [" + token.getId().toString() + "] expired.");
            tokenUserMap.remove(token);
        }
        logger.info("Finished removing expired tokens.");
    }

    protected UserSession getUserForToken(AuthToken token) throws IllegalTokenChangeException {
        if (!checkToken(token)) {
            return null;
        }
        return tokenUserMap.get(token);
    }
}
