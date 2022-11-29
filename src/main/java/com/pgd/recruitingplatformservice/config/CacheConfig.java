package com.pgd.recruitingplatformservice.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    /**
     *Variable with time of cache. It was taken from Careers by PGD.
     */
    @Value("${app.cache.time-to-live}")
    private Integer timeToLive;

    /**
     *The maximum number of cache entries or bytes a cache can use in
     * local heap memory.
     */
    @Value("${app.cache.max-entries-local-heap}")
    private Integer maxEntriesLocalHeap;

    /**
     * Arbitrary definition of CACHE_NAME.
     */
    private static final String CACHE_NAME = "jobsCache";

    /**
     * Definition of Cache properties.
     * @return Instance of Cache.
     */
    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration jobsCache =
                new CacheConfiguration();
        jobsCache.setName(CACHE_NAME);
        jobsCache.setMaxEntriesLocalHeap(maxEntriesLocalHeap);
        jobsCache.setTimeToLiveSeconds(timeToLive);
        net.sf.ehcache.config.Configuration config =
                new net.sf.ehcache.config.Configuration();
        config.addCache(jobsCache);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    /**
     * CacheManager.
     * @return Instance of Cache.
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }
}
