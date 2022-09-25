package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  
		  List<Person> persons = new ArrayList<Person>();
		  Person person = new Person();
		  person.setFirstName("Manuel");
		  person.setLastName("Vazquez");
		  persons.add(person);
		  
		  Person person1 = new Person();
		  person.setFirstName("Adrian");
		  person.setLastName("Romero");
		  persons.add(person1);
		  
		  Person person2 = new Person();
		  person.setFirstName("Bogdan Marian");
		  person.setLastName("Stefan");
		  persons.add(person2);
		  
		  Person person3 = new Person();
		  person.setFirstName("Angel");
		  person.setLastName("Muñoz");
		  persons.add(person3);
		  
		  Person person4 = new Person();
		  person.setFirstName("Alejandro");
		  person.setLastName("Mateo");
		  persons.add(person4);
		  
		  Person person5 = new Person();
		  person.setFirstName("Jorge");
		  person.setLastName("Limon");
		  persons.add(person5);
		  
		  model.put("persons", persons);
		  model.put("title", "My Project");
		  model.put("group", "L7-5");
	    return "welcome";
	  }
}
