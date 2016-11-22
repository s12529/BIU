package com.example.vaadindemo.domain;

import java.util.UUID;

import com.example.vaadindemo.service.Sex;

public class Person {
	
	private String firstName;
	private String lastName;
	private UUID id;
	private int yob;
	private Sex sex;
	
	//methods
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", yob=" + yob + "]";
	}
	
	//constructors
	public Person(String firstName, String lastName, int yob, Sex sex) {
		super();
		setSex(sex);
		setFirstName(firstName);
		setLastName(lastName);
		setId(UUID.randomUUID());
		setYob(yob);
	}
	
	public Person(Person person) {
		setSex(person.getSex());
		setFirstName(person.getFirstName());
		setLastName(person.getLastName());
		setId(UUID.randomUUID());
		setYob(person.getYob());
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (sex != other.sex)
			return false;
		if (yob != other.yob)
			return false;
		return true;
	}

	
}
