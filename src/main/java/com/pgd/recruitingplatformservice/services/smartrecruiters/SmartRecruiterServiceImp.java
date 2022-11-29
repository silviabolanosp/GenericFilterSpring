package com.pgd.recruitingplatformservice.services.smartrecruiters;

import com.google.gson.Gson;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.DataJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.ResponseApplyForJobSr;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmartRecruiterServiceImp implements SmartRecruiterService {
    /**
     * RestTemplate instance to call web api.
     */
    private final RestTemplate restTemplate;
    /**
     * Gson instance to serialise response data.
     */
    private Gson gson;
    /**
     * Base url of SR endpoint.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.base-url}")
    private String baseUrl;
    /**
     * Base uri of SR endpoint to apply for job.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.job-post-apply-url}")
    private String jobPostApplyUrl;
    /**
     * Token to send as header to SR endpoint request.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.smart-token}")
    private String smartToken;
    /**
     * Limit number of registers to receive from SR endpoint response.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.limit}")
    private int limit;
    /**
     * Filter to receive data from SR endpoint response.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.country}")
    private String country;
    /**
     * Filter to ask for data from SR endpoint response.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.field-id}")
    private String fielId;
    /**
     * Filter to ask for data from SR endpoint response.
     * Taken from application.yml file.
     */
    @Value("${app.smartrecruiters.brand}")
    private String brand;

    /**
     * Constructor of the class to instance local restTemplate object.
     * @param restTemplateObject
     */
    public SmartRecruiterServiceImp(
            final RestTemplate restTemplateObject
    ) {
        this.restTemplate = restTemplateObject;
    }

    /**
     * Get Job Id iterating around the limit variable.
     * @return List<ResumeJob>
     */
    @Override
    public List<ResumeJob> getIdJobs() {
        List<ResumeJob> jobs = new ArrayList<>();
        int offset = 0;
        int totalJobsFound = 0;

        do {
            DataJob response = getLimitJobs(offset);
            if (response != null) {
                jobs.addAll(response.getContent());
                offset += limit;
                totalJobsFound = response.getTotalFound();
            }
        } while (totalJobsFound > offset);

        return jobs;
    }

    private DataJob getLimitJobs(
            final int offset
    ) throws NullPointerException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        DataJob recruiterJobsResponse = DataJob.builder().build();

        ResponseEntity<DataJob> response =
                this.restTemplate.exchange(
                        baseUrl
                                + "/postings?limit="
                                + limit
                                + "&custom_field.COUNTRY="
                                + country
                                + "&custom_field."
                                + fielId
                                + "="
                                + brand
                                + "&offset="
                                + offset,
                        HttpMethod.GET,
                        request,
                        DataJob.class);
        try {
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                recruiterJobsResponse = response.getBody();
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        }

        return recruiterJobsResponse;
    }

    /**
     * Get job detail after first call SR end points to obtain list of jobs.
     * @param idsJob
     * @return List<JobSR>
     */
    @Override
    public List<JobSR> getJobDetail(
            final List<ResumeJob> idsJob
    ) {
        return idsJob
                .parallelStream()
                .map(
                        resumeJob -> getJobById(
                                resumeJob.getId(),
                                resumeJob.getReleasedDate()
                        )
                )
                .collect(Collectors.toList());
    }

    private JobSR getJobById(
            final String id, final String releaseDate
    ) throws NullPointerException {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObjectResponse;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        JobSR job = JobSR.builder().build();

        ResponseEntity<String> response =
                this.restTemplate.exchange(
                        baseUrl
                                + "/postings/"
                                + id,
                        HttpMethod.GET,
                        request,
                        String.class);

        try {
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                gson = new Gson();
                jsonObjectResponse = new JSONObject(response.getBody());
                job = gson.fromJson(jsonObjectResponse.toString(), JobSR.class);
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        }

        return job.toBuilder().releasedDate(releaseDate).build();
    }

    /**
     * Call SR endPoint and create new apply for job.
     * @param applyForJobLeadSr
     * @param srUuid
     * @return ResponseEntity<String> with message from SR.
     */
    @Override
    public ResponseApplyForJobSr
    applyForJobLead(
            final ApplyForJobLeadSr applyForJobLeadSr,
            final String srUuid
    ) {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObjectResponse;
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-SmartToken", smartToken);
        HttpEntity<Object> request =
                new HttpEntity<>(applyForJobLeadSr, headers);
        ResponseApplyForJobSr responseApply =
                ResponseApplyForJobSr
                        .builder()
                        .build();

        ResponseEntity<String> response;

        try {
            response =
                    this.restTemplate.exchange(
                            jobPostApplyUrl + "/" + srUuid + "/candidates",
                            HttpMethod.POST,
                            request,
                            String.class
                    );
        } catch (HttpClientErrorException e) {
            response =
                    new ResponseEntity<>(
                            e.getResponseBodyAsString(),
                            e.getStatusCode()
                    );
        }

        try {
            if (response.hasBody()) {
                gson =
                        new Gson();
                jsonObjectResponse =
                        new JSONObject(response.getBody());
                responseApply =
                        gson.fromJson(
                                jsonObjectResponse.toString(),
                                ResponseApplyForJobSr.class
                        );
            }
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        }

        return responseApply;
    }
}
