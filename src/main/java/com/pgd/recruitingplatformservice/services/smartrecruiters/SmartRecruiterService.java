package com.pgd.recruitingplatformservice.services.smartrecruiters;


import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.ResponseApplyForJobSr;

import java.util.List;

public interface SmartRecruiterService {
    List<ResumeJob> getIdJobs();
    List<JobSR> getJobDetail(List<ResumeJob> idsJob);
    ResponseApplyForJobSr
    applyForJobLead(
            ApplyForJobLeadSr applyForJobLead,
            String srUuid
    );
}
