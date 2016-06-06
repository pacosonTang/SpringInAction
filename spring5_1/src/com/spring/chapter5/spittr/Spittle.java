package com.spring.chapter5.spittr;

public class Spittle {

	private final int id;
	private final String name;
	
	public Spittle(int id, String name) {	
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
