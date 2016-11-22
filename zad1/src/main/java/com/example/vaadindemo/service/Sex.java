package com.example.vaadindemo.service;

public enum Sex {
	
	OTHER("Inna"), MALE("M�czyzna"), FEMALE("Kobieta");
	
	private String desc;
	
	Sex(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}
}
