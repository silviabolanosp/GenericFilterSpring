package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class ResponseApplyForJobSr {
    /**
     * UUID
     */
    private String id;
    /**
     * Date of apply
     */
    private String createdOn;
    /**
     * Url of application.
     */
    private String candidatePortalUrl;
    /**
     * Only on wrong request apply.
     */
    private String code;
    /**
     * Only on wrong request apply.
     */
    private String message;
}
