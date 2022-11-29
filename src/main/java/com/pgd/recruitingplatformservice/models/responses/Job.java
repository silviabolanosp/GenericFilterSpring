
package com.pgd.recruitingplatformservice.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class Job {
    /**
     * Name of open position. From Smart Recruiters.
     */
    private String jobTitle;
    /**
     * Numerical Id. From Smart Recruiters.
     */
    private String srIdRawId;
    /**
     * UUID of open position. From Smart Recruiters.
     */
    private String srUuid;
    /**
     * srIdRawId + jobTitle.
     */
    private String jobId;
    /**
     * Category of open position. From Smart Recruiters.
     */
    private String jobCategory;
    /**
     * Post date of open position. From Smart Recruiters.
     */
    private String postDate;
    /**
     * Alphanumeric ref number. From Smart Recruiters.
     */
    private String refNumber;
    /**
     * Country of the open position.
     */
    private String country;
    /**
     * City of the open position.
     */
    private String city;
    /**
     * Overview of the open position. From Smart Recruiters.
     */
    private String overview;
    /**
     * Qualifications required by open position.
     */
    private String qualifications;
    /**
     * Responsibilities of open position.
     */
    private String responsibilities;
    /**
     * Benefits of open position.
     */
    private String benefits;
    /**
     * Priority of open position.
     */
    private String priority;
    /**
     * Priority Value of open position. Frequently Low.
     */
    private String priorityValue;
    /**
     * Priority Order of open position. Frequently 2.
     */
    private Integer priorityOrder;
    /**
     * Url referent to open position and apply for it.
     */
    private String applyUrl;
    /**
     * Url referent to open position and refer some candidate for it.
     */
    private String referUrl;
    /**
     * Url referent to open position and look all details.
     */
    private String url;
}