package com.spring.spittr.repository;

import com.spring.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

  String findPassByUsername(String username);
}
