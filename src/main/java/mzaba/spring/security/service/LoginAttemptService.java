package mzaba.spring.security.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import com.google.common.cache.LoadingCache;
import mzaba.spring.security.data.model.CachedValue;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginAttemptService {

    public static final int MAX_ATTEMPTS = 3;
    public static final int BLOCKING_DURATION_SECONDS = 300;
    private final LoadingCache<String, CachedValue> attemptsCache;

    public LoginAttemptService(){
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(BLOCKING_DURATION_SECONDS, TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public CachedValue load(String s) throws Exception {
                        return new CachedValue(0,null,null);
                    }} );
    }

    public void loginFailed(final String key) {
        try{
            var cachedValue = attemptsCache.get(key);
            cachedValue.registerAttempt();

            if(isBlocked(key) && cachedValue.getBlockedTimestamp()==null) {

            }
        } catch (ExecutionException e) {
            log.error("Failed to get cached value", e );
        }
    }

    public boolean isBlocked(final String key) {
        try{
            return attemptsCache.get(key).getAttempts() >= MAX_ATTEMPTS;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public void loginSuccess(String key) {
        var cachedLoginAttempts = new CachedValue(0, null, null);
        log.info("Resetting login attempts.");
        attemptsCache.put(key, cachedLoginAttempts);
    }
}
