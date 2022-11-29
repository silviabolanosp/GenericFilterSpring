package com.pgd.recruitingplatformservice.models.requests.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class ApplyForJobLeadSr {
    /**
     * A string containing first name of candidate.
     */
    private String firstName;
    /**
     * A string containing last name of candidate.
     */
    private String lastName;
    /**
     * A string containing email address of candidate.
     */
    private String email;
    /**
     * A string containing phone number of candidate.
     */
    private String phoneNumber;
    /**
     * A WebSr containing LinkedIn information account.
     */
    private WebSr web;
}
