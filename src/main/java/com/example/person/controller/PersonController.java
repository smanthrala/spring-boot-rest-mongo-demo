package com.example.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.person.dto.Person;
import com.example.person.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping(method = RequestMethod.GET, value="/person")
	public Person savePerson(@RequestParam(value = "fname", required = false) String fname, 
			@RequestParam(value = "lname", required = false) String lname,
			@RequestParam(value = "id", required = true) String id) throws Exception{
		
		Person p = new Person();
		p.setFirstName(fname);
		p.setLastName(lname);
		if(id!=null)
			p.setId(Long.valueOf(id));

		try {
			service.createPerson(p);
		} catch (Exception e) {
			service.updatePerson(p);
		}
		
		return p;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/person")
	public Person savePerson(Person p) throws Exception{
		
		try {
			service.createPerson(p);
		} catch (Exception e) {
			service.updatePerson(p);
		}
		
		return p;
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/person/all")
	public List<Person> getPersons() throws Exception{
		return service.getAllPersons();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/person/{id}")
	public Person getPerson(@PathVariable("id") Long id) throws Exception{
		try {
			return service.getPerson(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
