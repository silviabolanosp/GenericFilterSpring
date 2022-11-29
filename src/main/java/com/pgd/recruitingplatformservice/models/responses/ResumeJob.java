package com.pgd.recruitingplatformservice.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class ResumeJob {
    /**
     * String with basic element of a job obtained from Smart Recruiter.
     */
    private String id;
    /**
     * String with releasedDate of a job obtained from Smart Recruiter.
     */
    private String releasedDate;
}
