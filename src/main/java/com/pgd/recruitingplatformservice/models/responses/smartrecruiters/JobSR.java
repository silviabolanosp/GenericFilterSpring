package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class JobSR {
    /**
     * Numeric Id of open position.
     */
    private String id;
    /**
     * Name of open position.
     */
    private String name;
    /**
     * UUID of open position.
     */
    private String uuid;
    /**
     *
     */
    private String jobAdId;
    /**
     * Alphanumeric data of open position.
     */
    private String refNumber;
    /**
     *
     */
    private Company company;
    /**
     *
     */
    private Location location;
    /**
     * Post date of open position.
     */
    private String releasedDate;
    /**
     * Url referent to open position and look all details.
     */
    private String postingUrl;
    /**
     * Url referent to open position and apply for it.
     */
    private String applyUrl;
    /**
     * Url referent to open position and refer some candidate for it.
     */
    private String referralUrl;
    /**
     *
     */
    private JobAd jobAd;
    /**
     *
     */
    private Label department;
}
