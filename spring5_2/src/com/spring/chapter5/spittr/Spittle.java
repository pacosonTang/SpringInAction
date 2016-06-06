package com.spring.chapter5.spittr;

public class Spittle {

	private int id;
	private String name;
	private String address;
	
	public Spittle(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Spittle(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
