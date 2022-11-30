package com.example.genericFilter.controllers;

import com.example.genericFilter.builder.GenericSpecificationsBuilder;
import com.example.genericFilter.entities.PersonEntity;
import com.example.genericFilter.filter.CriteriaParser;
import com.example.genericFilter.filter.PersonSpecification;
import com.example.genericFilter.models.responses.Person;
import com.example.genericFilter.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Generic Filter Controller",
        description = "This service can be used to find people.")
public class RecruitingController {

    /**
     * Dependency injection of PersonService to get job List and apply for job.
     */
    @Autowired
    private PersonService personService;
    /**
     * Dependency injection of CountriesService to get countries List.
     */


    /**
     * Default error message.
     */
    private static final String ERROR_500 = "500 Internal Server Error[";

    Logger logger = LoggerFactory.getLogger(RecruitingController.class);

    /**
     * api/v1/jobs
     * Action: get.
     * @return ResponseEntity with jobs list
     */
    @Operation(summary = "Returns list of people",
            description = "List of people without filter.",
            tags = {"Generic Filter Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Person.class
                            )
                    )})})
    @GetMapping("/persons")
    public ResponseEntity<Object> getPersons() {
        logger.info("controller /jobs {\"\": \"\"} ");
        try {
            Optional<List<Person>> jobs =
                    personService
                            .getPersonList();
            if (jobs.isPresent() && !jobs.get().isEmpty()) {
                return ResponseEntity
                        .ok(jobs.get());
            }
            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body(ERROR_500 + ex + "]");
        }
    }
    
    @Operation(summary = "Returns a filtered list of jobs",
            description = "List of people " +
                    "filtered according to different job attributes",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Person.class
                                    )
                            )})})
    @GetMapping("/filter")
    public ResponseEntity<Object> getAllSearchCriteria(
            @RequestParam(value = "search") String search) {
        Specification<PersonEntity> spec = resolveSpecificationFromInfixExpr(search);
        logger.info("controller /filter {\"\": \"\"} ");
        try {
            Optional<List<Person>> jobs =
                    personService
                            .getPersonList(spec);
            if (jobs.isPresent() && !jobs.get().isEmpty()) {
                return ResponseEntity
                        .ok(jobs.get());
            }
            return ResponseEntity.ok(Collections.emptyList());
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body(ERROR_500 + ex + "]");
        }

    }

    private Specification<PersonEntity> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<PersonEntity> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), PersonSpecification::new);
    }

}
