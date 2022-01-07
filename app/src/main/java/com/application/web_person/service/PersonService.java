package com.application.web_person.service;

import com.application.web_person.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    List<PersonEntity> getAllPerson();
    void save(PersonEntity personEntity);
}
