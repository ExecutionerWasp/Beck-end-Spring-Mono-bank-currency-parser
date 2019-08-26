package com.app.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Alvin
 **/
@Service
@RequiredArgsConstructor
public class CacheService {

    @NonNull
    private final CacheManager cacheManager;

    @Scheduled(fixedRate = 15000)
    public void clearCacheSchedule(){
        cacheManager.getCacheNames()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
