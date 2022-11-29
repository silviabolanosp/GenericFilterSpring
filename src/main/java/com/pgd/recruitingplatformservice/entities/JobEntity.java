package com.pgd.recruitingplatformservice.entities;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Job")
public class JobEntity {

    /**
     * Identity of Recruiting Platform.
     */
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Name of open position.
     */
    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    /**
     * Numerical Id of open position.
     */
    @Column(name = "sr_id_raw_id", nullable = false)
    private String srIdRawId;
    /**
     * UUID of open position.
     */
    @Column(name = "sr_uu_id", nullable = false)
    private String srUuid;
    /**
     * srIdRawId-jobTitle without blank spaces.
     */
    @Column(name = "job_id", nullable = false)
    private String jobId;
    /**
     * Category of open position.
     */
    @Column(name = "job_category", nullable = false) // --> same as AREAS in filters?
    private String jobCategory;
    /**
     * Post date of open position.
     */
    @Column(name = "post_date", nullable = false)
    private String postDate;
    /**
     * Number of reference.
     */
    @Column(name = "ref_number", nullable = false)
    private String refNumber;
    /**
     * Country of open position.
     */
    @Column(nullable = false) // --> location is a composed value of country and city?
    private String country;
    /**
     * City of open position.
     */
    @Column(nullable = false)
    private String city;
    /**
     * Overview of the open position. From Smart Recruiters.
     */
    @Column(nullable = false, length = 5000)
    private String overview;
    /**
     * Qualifications required by open position.
     */
    @Column(nullable = false, length = 5000)
    private String qualifications;
    /**
     * Responsibilities of open position.
     */
    @Column(nullable = false, length = 5000)
    private String responsibilities;
    /**
     * Benefits of open position.
     */
    @Column(nullable = false, length = 5000)
    private String benefits;
    /**
     * Priority of open position.
     */
    @Column()
    private String priority;
    /**
     * Priority Value of open position. Frequently Low.
     */
    @Column(name = "priority_value", nullable = false)
    private String priorityValue;
    /**
     * Priority Order of open position. Frequently 2.
     */
    @Column(name = "priority_order", nullable = false)
    private Integer priorityOrder;
    /**
     * Url referent to open position and apply for it.
     */
    @Column(name = "apply_url", nullable = false)
    private String applyUrl;
    /**
     * Url referent to open position and refer some candidate for it.
     */
    @Column(name = "refer_url", nullable = false)
    private String referUrl;
    /**
     * Url referent to open position and look all details.
     */
    @Column(nullable = false)
    private String url;

    /**
     * Seniority refers to being ranked higher in an organization since you have been there longer
     */
    @Column() // --> Should this be an enum? Jr, Mid, Sr
    private SeniorityLevel seniority;

    /**
     * Compensation
     */
    @Column() // --> are we storing a range or a specific number, if it's a range would it be a String
    private String salaryRange;

    /**
     * Modalities
     */
    @Column() // --> what is this?
    private String modality;





}
