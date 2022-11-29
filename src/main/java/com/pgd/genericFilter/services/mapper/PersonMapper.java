package com.pgd.genericFilter.services.mapper;

import com.pgd.genericFilter.entities.PersonEntity;
import com.pgd.genericFilter.models.responses.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

    List<PersonEntity> personsToPersonsEntity(List<Person> persons);

    List<Person> personsEntityToPersons(List<PersonEntity> jobEntities);
}
