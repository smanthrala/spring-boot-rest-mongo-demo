package com.example.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.person.dao.PersonRepository;
import com.example.person.dto.Person;

@Service
public class PersonService {

	
	@Autowired
	private PersonRepository repo;
	
	public Person getPerson(Long id) throws Exception{
		if(id != null){
			List<Person> persons = repo.findById(id);
			if(persons!=null && !persons.isEmpty())
				return persons.get(0);
			else
				throw new Exception("No Persons found for ID:"+id);
		}
		throw new Exception("ID is null");
	}

	public List<Person> getAllPersons() throws Exception{
		return repo.findAll();
	}

	public Person createPerson(Person p) throws Exception{
		if(p!=null && p.getId()!=null){
			try {
				Person existingPerson = getPerson(p.getId());
				if(existingPerson!=null)
					throw new Exception("Person with ID:"+p.getId()+" already exists. Create Person failed.");
			} catch (Exception e) { }
			
		}
		repo.insert(p);
		return p;
	}

	public Person updatePerson(Person p) throws Exception{
		if(p!=null && p.getId()!=null){
			try {
				Person existingPerson = getPerson(p.getId());
				if(existingPerson==null)
					throw new Exception("Person with ID:"+p.getId()+" doesn't exist. Update Person failed.");
			} catch (Exception e) { }
			
		}
		repo.save(p);
		return p;
	}

}
