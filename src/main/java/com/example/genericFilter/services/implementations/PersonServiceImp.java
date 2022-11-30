package com.example.genericFilter.services.implementations;

import com.example.genericFilter.models.responses.Person;
import com.example.genericFilter.entities.PersonEntity;
import com.example.genericFilter.repositories.PersonEntityRepository;
import com.example.genericFilter.services.PersonService;
import com.example.genericFilter.services.mapper.GenericModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService {

    /**
     * Dependency injection of PersonEntityRepository to cache data.
     */
    @Autowired
    private PersonEntityRepository PersonEntityRepository;
    /**
     * Dependency injection of PersonMapper to map data
     * names from SR to Careers format.
     */

    private final GenericModelMapper mapper;

    public PersonServiceImp() {
        this.mapper = GenericModelMapper.singleInstance();
    }

    /**
     * Send data store in data base.
     * @return Optional<List<Person>>
     */
    @Override
    public Optional<List<Person>> getPersonList() {
        List<PersonEntity> jobEntities =
                PersonEntityRepository
                        .findAll();
        List<Person> jobs =
                mapper
                        .personsEntityToPersons(jobEntities);
        return Optional
                .of(jobs);
    }

    @Override
    public Optional<List<Person>> getPersonList(Specification<PersonEntity> spc) {



        List<PersonEntity> jobEntities =
                PersonEntityRepository
                        .findAll(spc);
        List<Person> jobs =
                mapper
                        .personsEntityToPersons(jobEntities);
        return Optional
                .of(jobs);
    }

}
