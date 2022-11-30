package com.pgd.genericFilter.services.mapper;

import com.pgd.genericFilter.entities.PersonEntity;
import com.pgd.genericFilter.models.responses.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;


public class GenericModelMapper {
    private final ModelMapper mapper = new ModelMapper();

    private static GenericModelMapper instance;

    private GenericModelMapper() {
    }

    public static GenericModelMapper singleInstance() {
        if (instance == null) {
            instance = new GenericModelMapper();
        }
        return instance;
    }

    public List<PersonEntity> personsToPersonsEntity(List<Person> persons) {
        return mapper.map(persons, new TypeToken<List<Person>>() {}.getType());
    }

    public List<Person> personsEntityToPersons(List<PersonEntity> personEntities) {
        return mapper.map(personEntities, new TypeToken<List<PersonEntity>>() {}.getType());
    }



}