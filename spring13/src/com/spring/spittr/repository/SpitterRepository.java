package com.spring.spittr.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.spring.spittr.Spitter;

public interface SpitterRepository {

	@CachePut("spitterCache")
	Spitter save(Spitter spitter);

	@Cacheable("spitterCache")
	Spitter findByUsername(String username);

	String findPassByUsername(String username);
	
	int getItemSum();
	
	List<Spitter> findSpitters(int limit, int offset);
	
	@CacheEvict("spitterCache") // highlight line.
	int delete(String username);
}
