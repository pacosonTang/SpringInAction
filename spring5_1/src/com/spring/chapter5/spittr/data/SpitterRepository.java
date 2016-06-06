package com.spring.chapter5.spittr.data;

import com.spring.chapter5.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
