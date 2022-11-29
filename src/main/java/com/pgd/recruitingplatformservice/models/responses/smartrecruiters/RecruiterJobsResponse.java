package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class RecruiterJobsResponse {
    /**
     * Total of open positions.
     */
    private int totalFound;
    /**
     * List of resume jobs.
     */
    private List<ResumeJob> content;
}
