package com.example.person.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.example.person.dto.Person;

import java.lang.Long;
import java.util.List;
import java.lang.String;

@Component
public interface PersonRepository extends MongoRepository<Person, Long> {
	
	public List<Person> findById(Long id);
	public List<Person> findByLastName(String lastname);
	public List<Person> findByFirstName(String firstname);
	
}
