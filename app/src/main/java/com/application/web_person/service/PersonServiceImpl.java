package com.application.web_person.service;

import com.application.web_person.entity.PersonEntity;
import com.application.web_person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> getAllPerson() {
         return personRepository.findAll();
    }

    @Override
    public void save(PersonEntity personEntity) {
        personRepository.save(personEntity);

    }
}

