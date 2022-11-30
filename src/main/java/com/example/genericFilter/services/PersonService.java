package com.example.genericFilter.services;

import com.example.genericFilter.models.responses.Person;
import com.example.genericFilter.entities.PersonEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<List<Person>> getPersonList();
    Optional<List<Person>> getPersonList(Specification<PersonEntity> query);
}
