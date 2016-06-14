package com.spring.spittr.web;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration
@EnableCaching // 启用缓存
public class CacheConfig {

	/*
	 * @Bean public CacheManager cacheManager() { // 声明缓存管理器 return new
	 * 		ConcurrentMapCacheManager();
	 * }
	 */

	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cm) {
		return new EhCacheCacheManager(cm);
	}

	@Bean  
    public EhCacheManagerFactoryBean ehcache() {  
		
		EhCacheManagerFactoryBean cacheFactory = new EhCacheManagerFactoryBean();
		cacheFactory.setConfigLocation(new ClassPathResource("com/spring/spittr/cache/ehcache.xml"));
		cacheFactory.setShared(true);
		return cacheFactory; 
	}
}