package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.models.responses.Job;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
public class JobsPriorityTest {

    @Mock
    private JobsPriority jobsPriority;

    @Test
    public void validatePriorityOrderAndPriorityValue_ThereAreValue() {

        ArrayList<Job> jobs = new ArrayList<>();

        jobs.add(
                Job.builder()
                        .jobTitle("Junior Recruiter")
                        .srIdRawId("743999812085881")
                        .srUuid("e6123328-d104-495a-b624-6b828d92ddef")
                        .jobId("743999812085881-junior-recruiter")
                        .jobCategory("Human_Resources")
                        .postDate("2021-02-15T12:48:41.000Z")
                        .refNumber("REF151393D")
                        .country("Colombia")
                        .city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999812085881")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter")
                        .build()
        );

        jobs.add(
                Job.builder()
                        .jobTitle("Associate Data Scientist")
                        .srIdRawId("743999863425467")
                        .srUuid("14156029-1abd-4d40-b2c4-8ddbad3b40d8")
                        .jobId("743999863425467-associate-data-scientist")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:55:54.000Z")
                        .refNumber("REF151364R")
                        .country("Mexico")
                        .city("Cancun")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999863425467")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist")
                        .build()
        );
        jobs.add(
                Job.builder()
                        .jobTitle("Data Analyst")
                        .srIdRawId("754568864546786")
                        .srUuid("b3222593-7e3f-48a4-b274-1451a6136ac0")
                        .jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:48:42.000Z")
                        .refNumber("REF151278B")
                        .country("Costa Rica")
                        .city("Heredia")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build()
        );

        jobs.add(
                Job.builder()
                        .jobTitle("Data Analyst 2")
                        .srIdRawId("7545688645467864")
                        .srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05")
                        .jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:48:42.000Z")
                        .refNumber("REF151278B")
                        .country("Costa Rica")
                        .city("Other city")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build()
        );

        jobs.add(
                Job.builder()
                        .jobTitle("Data Analyst 2")
                        .srIdRawId("7545688645467864")
                        .srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05")
                        .jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:48:42.000Z")
                        .refNumber("REF151278B")
                        .country("Costa Rica")
                        .city("Other city")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .priority("Normal")
                        .build()
        );

        Assert.assertEquals(0,jobsPriority.validatePriorityOrderAndPriorityValue(jobs).stream().filter(x -> x.getPriorityValue().isEmpty()).count());
        Assert.assertEquals(0,jobsPriority.validatePriorityOrderAndPriorityValue(jobs).stream().filter(x -> x.getPriorityOrder()< 1).count());
    }


}
