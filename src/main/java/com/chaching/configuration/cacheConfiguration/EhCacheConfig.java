package com.chaching.configuration.cacheConfiguration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class EhCacheConfig {

    CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManager());
    }

    private net.sf.ehcache.CacheManager ehCacheManager() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factoryBean.setShared(true);
        return factoryBean.getObject();
    }

    // To check whether data is storing, we can use "window + R" and search for "%temp%", and then find the name of which is given as cacheName
    // Location is  -> C:\Users\hp\AppData\Local\Temp
    
}
