package com.spring.chapter5.spittr.data;

import java.util.List;

import com.spring.chapter5.spittr.Spittle;

public interface SpittleRepository {

  List<Spittle> findSpittles(long limit, int offset);
  Spittle findSpittle(int id); 
  int getItemSum();
}
