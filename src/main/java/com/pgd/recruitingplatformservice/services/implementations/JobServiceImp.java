package com.pgd.recruitingplatformservice.services.implementations;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.helpers.ConverterDate;
import com.pgd.recruitingplatformservice.models.requests.ApplyForJobLead;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResponseApplyForJob;
import com.pgd.recruitingplatformservice.repositories.JobEntityRepository;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.ResponseApplyForJobSr;
import com.pgd.recruitingplatformservice.services.JobService;
import com.pgd.recruitingplatformservice.services.mapper.ApplyJobMapper;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import com.pgd.recruitingplatformservice.services.smartrecruiters.SmartRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService, ConverterDate {
    /**
     * Dependency injection of SmartRecruiterService to call
     * service of apply for job.
     */
    @Autowired
    private SmartRecruiterService recruiterService;
    /**
     * Dependency injection of JobEntityRepository to cache data.
     */
    @Autowired
    private JobEntityRepository jobEntityRepository;
    /**
     * Dependency injection of JobMapper to map data
     * names from SR to Careers format.
     */
    @Autowired
    private JobMapper jobMapper;
    /**
     * Dependency injection of ApplyJobMapper to map data
     * to apply from SR to Careers format.
     */
    @Autowired
    private ApplyJobMapper applyJobMapper;

    /**
     * Send data store in data base to cache object.
     * @return Optional<List<Job>>
     */
    @Override
    @Cacheable(value = "jobsCache", key = "'jobs'")
    public Optional<List<Job>> getJobList() {
        List<JobEntity> jobEntities =
                jobEntityRepository
                        .findAll();
        List<Job> jobs =
                jobMapper
                        .jobsEntityToJobs(jobEntities);
        return Optional
                .of(jobs);
    }

    /**
     * Implementation of method to apply for job.
     * @param applyForJobLead basic data of open position and application.
     * @return optional of responseApplyForJob
     */
    @Override
    public Optional<ResponseApplyForJob> applyForJobLead(
          final ApplyForJobLead applyForJobLead
    ) {
        ApplyForJobLeadSr applyForJobLeadSr =
                applyJobMapper
                        .applyForJobLeadToApplyForJobLeadSr(applyForJobLead);

        ResponseApplyForJobSr applyForJob =
                recruiterService
                        .applyForJobLead(
                                applyForJobLeadSr, applyForJobLead.getSrUuid()
                        );

        ResponseApplyForJob responseApplyForJob =
                new ResponseApplyForJob()
                        .toBuilder()
                        .leadId(applyForJobLead.getLeadId())
                        .message("New lead created")
                        .smartRecruiters(applyForJob.toString())
                        .build();

        return Optional
                .of(responseApplyForJob);
    }

    @Override
    public Optional<List<Job>> getJobList(Specification<JobEntity> spc) {

        logger.info(spc.toString());

        List<JobEntity> jobEntities =
                jobEntityRepository
                        .findAll(spc);
        List<Job> jobs =
                jobMapper
                        .jobsEntityToJobs(jobEntities);
        return Optional
                .of(jobs);
    }

}
