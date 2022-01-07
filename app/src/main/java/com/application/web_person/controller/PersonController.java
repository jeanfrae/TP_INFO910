package com.application.web_person.controller;

import com.application.web_person.entity.PersonEntity;
import com.application.web_person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(path="/person")
    public String allPersonne(Model model){
        List<PersonEntity> list =personService.getAllPerson();
        model.addAttribute("listPerson",list);
        return "index";
    }
    @GetMapping(path="/showNewPerson")
    public String showNewPerson(Model model) {
        PersonEntity person= new PersonEntity();
        model.addAttribute("person",person);
        return "FormPerson";
    }
    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("person") PersonEntity person) {
        // save employee to database
        personService.save(person);
        return "redirect:person";
    }

}
