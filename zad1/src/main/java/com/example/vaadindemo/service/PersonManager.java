package com.example.vaadindemo.service;


import java.util.ArrayList;
import java.util.List;

import com.example.vaadindemo.domain.Person;

public class PersonManager {
	
	private List<Person> persons;
	
	public PersonManager() {
		persons = new ArrayList<Person>();
	}
	
	public void addPerson(Person person) {
		persons.add(new Person(person));
	}

	public void deletePerson(Person person) {
		persons.remove(person);
	}
	
	public void deleteSpecificPerson(Person person) {
		Person toRemove = null;
		for (Person p: persons) {
			if (p.getId().compareTo(person.getId()) == 0){
				toRemove = p;
				break;
			}
		}
			persons.remove(toRemove); 
	}
	
	public void updatePerson(Person person) {
		Person updatedPerson = new Person(person);
		updatedPerson.setId(person.getId());
		deleteSpecificPerson(person);
		persons.add(updatedPerson);
	}
	
	public List<Person> findAll() {
		return this.persons;
	}
}
