package com.pgd.recruitingplatformservice.controllers;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.filter.CriteriaParser;
import com.pgd.recruitingplatformservice.filter.GenericSpecificationsBuilder;
import com.pgd.recruitingplatformservice.filter.JobSpecification;
import com.pgd.recruitingplatformservice.filter.SpecSearchCriteria;
import com.pgd.recruitingplatformservice.models.requests.ApplyForJobLead;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResponseApplyForJob;
import com.pgd.recruitingplatformservice.models.responses.StringResults;
import com.pgd.recruitingplatformservice.services.CategoriesService;
import com.pgd.recruitingplatformservice.services.CountriesService;
import com.pgd.recruitingplatformservice.services.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Recruiting Platform Controller",
        description = "This Centralized service can be used for getting access"
                + "on Smart Recruiting Platform. \n"
                + "Recruiting platform service is an internal PGD"
                + "project documented at "
                + "https://wiki.prodigious.com/display/IP/"
                + "Recruiting+Platform+Service.")
public class RecruitingController {

    /**
     * Dependency injection of JobService to get job List and apply for job.
     */
    @Autowired
    private JobService jobService;
    /**
     * Dependency injection of CountriesService to get countries List.
     */
    @Autowired
    private CountriesService countriesService;
    /**
     * Dependency injection of CategoriesService to get categories List.
     */
    @Autowired
    private CategoriesService categoriesService;





    /**
     * Default error message.
     */
    private static final String ERROR_500 = "500 Internal Server Error[";

    Logger logger = LoggerFactory.getLogger(RecruitingController.class);
    /**
     * api/v1/countries.
     * Action: get.
     * @return ResponseEntity With countries list.
     */
    @Operation(summary = "Returns list of countries",
            description = "List of countries were there are available"
                    + " positions. Please realize there is a cache data"
                    + " what is updated every 11 hours from Smart "
                    + "Recruiting Platform.",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = StringResults.class
                            )
                    )})})
    @GetMapping("/countries")
    public ResponseEntity<Object> getCountries() {
        logger.info("controller /countries {\"\": \"\"} ");
        StringResults results =
                StringResults.builder().build();
        try {
            Optional<StringResults> countries =
                    countriesService.getCountriesList();
            if (countries.isPresent()) {
                results =
                        countries.get();
            }
            return ResponseEntity
                    .ok(results);
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body(ERROR_500 + ex + "]");
        }
    }

    /**
     * api/v1/categories.
     * Action: get.
     * @return ResponseEntity With categories list.
     */
    @Operation(summary = "Returns list of categories",
            description = "List of categories of available positions."
                    + " Please realize there is a cache data what is "
                    + "updated every 11 hours from Smart Recruiting Platform.",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = StringResults.class
                            )
                    )})})
    @GetMapping("/categories")
    public ResponseEntity<Object> getCategories() {
        logger.info("controller /categories {\"\": \"\"} ");
        StringResults results =
                StringResults
                        .builder()
                        .build();
        try {
            Optional<StringResults> categories =
                    categoriesService
                            .getCategoriesList();
            if (categories.isPresent()) {
                results =
                        categories
                                .get();
            }
            return ResponseEntity
                    .ok(results);
        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .body(ERROR_500 + ex + "]");
        }
    }

    /**
     * api/v1/jobs
     * Action: get.
     * @return ResponseEntity with jobs list
     */
    @Operation(summary = "Returns list of jobs",
            description = "List of available positions. Please realize"
                    + "there is a cache data what is updated every 11"
                    + "hours from Smart Recruiting Platform.",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = Job.class
                            )
                    )})})
    @GetMapping("/jobs")
    public ResponseEntity<Object> getJobs() {
        logger.info("controller /jobs {\"\": \"\"} ");
        try {
            Optional<List<Job>> jobs =
                    jobService
                            .getJobList();
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

    /**
     * api/v1/jobs-application
     * Action: post.
     * @param applyForJobLead basic data of job position and application.
     * @return ResponseEntity with message from Smart Recruiters.
     */
    @Operation(summary = "Apply for Job",
            description = "Connecting to Smart Recruiting Platform "
                    + "and create a new register of Job Apply.",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ResponseApplyForJob.class
                            )
                    )})})
    @PostMapping("/jobs-application")
    public ResponseEntity<ResponseApplyForJob> applyForJob(
            @Valid @RequestBody final ApplyForJobLead applyForJobLead
    ) {
        JSONObject infoObject = new JSONObject();
        infoObject.put("leadId", applyForJobLead.getLeadId().toString());

        String jsonInfo = "controller /jobs-application ";
        jsonInfo = jsonInfo.concat(infoObject.toString());
        logger.info(jsonInfo);
        try {
            Optional<ResponseApplyForJob> responseApply =
                    jobService
                            .applyForJobLead(applyForJobLead);

            return responseApply
                    .<ResponseEntity<ResponseApplyForJob>>map(
                            responseApplyForJob ->
                                    ResponseEntity
                                            .status(201)
                                            .body(responseApplyForJob)
                    )
                    .orElseGet(
                            () -> ResponseEntity.internalServerError().build()
                    );


        } catch (Exception ex) {
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    /**
    @GetMapping("/searchJobsByTag")
    public ResponseEntity<Object> getJobsByTag() {

    }
    */

    // filters: area, locations, modalities, salary range seniority

    @Operation(summary = "Returns a filtered list of jobs",
            description = "List of available positions and " +
                    "filtered according to different job attributes",
            tags = {"Recruiting Platform Controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = Job.class
                                    )
                            )})})
    @GetMapping("/filter")
    public ResponseEntity<Object> getAllSearchCriteria(
            @RequestParam(value = "search") String search) {
        Specification<JobEntity> spec = resolveSpecificationFromInfixExpr(search);
        logger.info("controller /filter {\"\": \"\"} ");
        try {
            Optional<List<Job>> jobs =
                    jobService
                            .getJobList(spec);
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

    private Specification<JobEntity> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<JobEntity> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), JobSpecification::new);
    }

}
