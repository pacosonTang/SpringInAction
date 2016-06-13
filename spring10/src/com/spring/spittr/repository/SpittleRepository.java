package com.spring.spittr.repository;

import java.util.List;

import com.spring.spittr.Spittle;

public interface SpittleRepository {

  List<Spittle> findSpittles(long limit, int offset);
  Spittle findSpittle(int id); 
  int getItemSum();
}
