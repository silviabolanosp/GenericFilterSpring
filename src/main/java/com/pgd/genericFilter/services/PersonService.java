package com.pgd.genericFilter.services;

import com.pgd.genericFilter.entities.PersonEntity;
import com.pgd.genericFilter.models.responses.Person;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<List<Person>> getPersonList();
    Optional<List<Person>> getPersonList(Specification<PersonEntity> query);
}
