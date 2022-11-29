package com.pgd.genericFilter.services.implementations;

import com.pgd.genericFilter.entities.PersonEntity;
import com.pgd.genericFilter.models.responses.Person;
import com.pgd.genericFilter.repositories.PersonEntityRepository;
import com.pgd.genericFilter.services.PersonService;
import com.pgd.genericFilter.services.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    private PersonMapper personMapper;

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
                personMapper
                        .jobsEntityToPersons(jobEntities);
        return Optional
                .of(jobs);
    }

    @Override
    public Optional<List<Person>> getPersonList(Specification<PersonEntity> spc) {



        List<PersonEntity> jobEntities =
                PersonEntityRepository
                        .findAll(spc);
        List<Person> jobs =
                personMapper
                        .jobsEntityToPersons(jobEntities);
        return Optional
                .of(jobs);
    }

}
