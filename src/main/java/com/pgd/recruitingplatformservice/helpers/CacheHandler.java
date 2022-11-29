package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.services.JobService;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class CacheHandler {
    /**
     * Dependency injection of JobService to get job List and apply for job.
     */
    @Autowired
    private JobService jobService;
    /**
     * Dependency injection of CacheManager to get Cache with job info.
     */
    @Autowired
    private CacheManager cacheManager;
    /**
     * Dependency injection of JobMapper to set data names from
     * Smart Recruiting style to Careers style.
     */
    @Autowired
    private JobMapper jobMapper;
    /**
     * Key value in cache object.
     */
    private static final String JOBS_KEY = "jobs";
    /**
     * Name cache object.
     */
    private static final String CACHE_NAME = "jobsCache";
    /**
     * Return list of jobs found on cache object.
     * @return List<Job>
     */
    public List<Job> getJobs() {
        Cache jobsCache =
                cacheManager
                        .getCache(CACHE_NAME);
        if (jobsCache != null && jobsCache.get(JOBS_KEY) != null) {
            return Objects
                    .requireNonNull(
                            jobsCache
                                    .get(JOBS_KEY, ArrayList<Job>::new)
                    );
        }
        return jobService
                .getJobList()
                .orElse(Collections.emptyList());
    }

    /**
     * Update cache object with list of jobs stored in database.
     * @param jobEntitiesSaved
     */
    public void updateCache(
            final List<JobEntity> jobEntitiesSaved
    ) {
        List<Job> jobs =
                jobMapper
                        .jobsEntityToJobs(jobEntitiesSaved);
        Cache jobsCache =
                cacheManager
                        .getCache(CACHE_NAME);
        if (jobsCache != null) {
            jobsCache.put(JOBS_KEY, jobs);
        }
    }

}
