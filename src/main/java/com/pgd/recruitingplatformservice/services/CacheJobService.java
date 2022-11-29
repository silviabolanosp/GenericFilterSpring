package com.pgd.recruitingplatformservice.services;

import com.pgd.recruitingplatformservice.models.responses.Job;

import java.util.List;

public interface CacheJobService {

    List<Job> getJobListSr();
}
