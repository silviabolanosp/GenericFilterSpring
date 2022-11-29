package com.pgd.recruitingplatformservice.services.implementations;

import com.pgd.recruitingplatformservice.helpers.ConverterDate;
import com.pgd.recruitingplatformservice.helpers.JobsPriority;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import com.pgd.recruitingplatformservice.services.CacheJobService;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import com.pgd.recruitingplatformservice.services.smartrecruiters.SmartRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CacheJobServiceImp implements CacheJobService, ConverterDate {
    /**
     * Dependency injection of SmartRecruiterService to get list of jobs.
     */
    @Autowired
    private SmartRecruiterService recruiterService;
    /**
     * Dependency injection of JobMapper to map data
     * names from SR to Careers format.
     */
    @Autowired
    private JobMapper jobMapper;

    /**
     * Return list of jobs mapped and sorted from SR.
     * @return List<Job>
     */
    @Override
    public List<Job> getJobListSr() {
        List<ResumeJob> idsJobs =
                recruiterService
                        .getIdJobs();
        List<JobSR> jobsSr =
                recruiterService
                        .getJobDetail(idsJobs);
        List<Job> jobs =
                jobMapper
                        .jobsSrToJob(jobsSr);
        jobs =
                JobsPriority
                        .validatePriorityOrderAndPriorityValue(jobs)
                        .stream()
                        .sorted(
                                Comparator
                                        .comparingInt(Job::getPriorityOrder)
                                        .thenComparingLong(
                                                job -> getPostDateValueFromParse(
                                                        job.getPostDate()
                                                )
                                        )
                        )
                        .collect(Collectors.toList());
        return jobs;
    }
}
