package com.sp.spring_security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author eric
 * @date 2022/3/22 12:28
 **/
@Slf4j
public class MyRememberMeTokenStore implements PersistentTokenRepository {

    private final Map<String, PersistentRememberMeToken> seriesTokens = new HashMap<>();



    public synchronized void createNewToken(PersistentRememberMeToken token) {
        PersistentRememberMeToken current = seriesTokens.get(token.getSeries());

        if (current != null) {
            throw new RuntimeException("Series Id '" + token.getSeries()
                    + "' already exists!");
        }

        seriesTokens.put(token.getSeries(), token);
    }

    public synchronized void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentRememberMeToken token = getTokenForSeries(series);

        PersistentRememberMeToken newToken = new PersistentRememberMeToken(
                token.getUsername(), series, tokenValue, new Date());

        // Store it, overwriting the existing one.
        seriesTokens.put(series, newToken);
    }

    public synchronized PersistentRememberMeToken getTokenForSeries(String seriesId) {
        log.info("remember me cookid {}",seriesId);
        return seriesTokens.get(seriesId);
    }

    public synchronized void removeUserTokens(String username) {
        Iterator<String> series = seriesTokens.keySet().iterator();

        while (series.hasNext()) {
            String seriesId = series.next();

            PersistentRememberMeToken token = seriesTokens.get(seriesId);

            if (username.equals(token.getUsername())) {
                series.remove();
            }
        }
    }

}
