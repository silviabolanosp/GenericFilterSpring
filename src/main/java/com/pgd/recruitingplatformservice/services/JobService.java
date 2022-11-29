package com.pgd.recruitingplatformservice.services;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.models.requests.ApplyForJobLead;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResponseApplyForJob;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Optional<List<Job>> getJobList();
    Optional<ResponseApplyForJob> applyForJobLead(ApplyForJobLead applyForJobLead);

    Optional<List<Job>> getJobList(Specification<JobEntity> query);
}
