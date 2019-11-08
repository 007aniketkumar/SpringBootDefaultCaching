package com.example.caching;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import java.util.concurrent.TimeUnit;

/*

Check this link https://stackoverflow.com/questions/38570211/how-to-have-multiple-cache-manager-configuration-in-spring-cache-java
T
Todo: Check the support for multiple cache managers and the corresponding configurations

 */

public class CacheConfiguration extends CachingConfigurerSupport {

    @Override
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                        CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build().asMap(), false);
            }
        };

        return cacheManager;
    }
}
