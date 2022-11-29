package com.pgd.recruitingplatformservice.services;


import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.Company;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobAd;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.Label;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.Location;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.Section;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.Sections;
import com.pgd.recruitingplatformservice.services.implementations.CacheJobServiceImp;
import com.pgd.recruitingplatformservice.services.implementations.JobServiceImp;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import com.pgd.recruitingplatformservice.services.smartrecruiters.SmartRecruiterService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CacheJobServiceTests {

    @Mock
    private SmartRecruiterService recruiterServiceMock;

    @Mock
    private JobMapper jobMapperMock;

    @InjectMocks
    private CacheJobServiceImp cacheJobServiceImp;

    @Test
    public void getJobListSrTest() {
        List<ResumeJob> idsJobs = Arrays.asList(
                ResumeJob.builder().id("743999812085881").releasedDate("2021-02-15T12:48:41.000Z").build(),
                ResumeJob.builder().id("743999863425467").releasedDate("2022-01-12T09:55:54.000Z").build(),
                ResumeJob.builder().id("754568864546786").releasedDate("2022-01-12T09:48:42.000Z").build());

        List<JobSR> jobsSr = Arrays.asList(
                JobSR.builder()
                        .id("743999812085881").name("Junior Recruiter").uuid("e6123328-d104-495a-b624-6b828d92ddef").jobAdId("32977390-172d-4759-a1cd-dcc79063d14d")
                        .refNumber("REF151393D").company(Company.builder().name("Publicis Groupe").identifier("PublicisGroupe").build())
                        .location(Location.builder().city("Bogota").country("co").address("Cra. 19a #90-12").remote(false).build())
                        .releasedDate("2021-02-15T12:48:41.000Z")
                        .postingUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter?oga=true")
                        .referralUrl("https://jobs.smartrecruiters.com/external-referrals/company/PublicisGroupe/publication/e6123328-d104-495a-b624-6b828d92ddef?dcr_id=DCRA1")
                        .jobAd(JobAd.builder().sections(Sections.builder().companyDescription(Section.builder().title("Company Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, description</p>").build()).jobDescription(Section.builder()
                                        .title("Job Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>").build()).qualifications(Section.builder()
                                        .title("Qualifications")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>").build()).additionalInformation(Section.builder()
                                        .title("Additional Information")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>").build()).build())
                                .build()).department(Label.builder().id("977507").label("Human_Resources").build()).build(),
                JobSR.builder()
                        .id("743999863425467").name("Associate Data Scientist").uuid("14156029-1abd-4d40-b2c4-8ddbad3b40d8").jobAdId("0d9fbb70-a7a5-49be-9855-bf4b1d4ee70d")
                        .refNumber("REF151364R").company(Company.builder().name("Publicis Groupe").identifier("PublicisGroupe").build())
                        .location(Location.builder().city("Bogota").country("co").address("Avenida Calle 26 92-32").remote(false).build())
                        .releasedDate("2022-01-12T09:55:54.000Z")
                        .postingUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist?oga=true")
                        .referralUrl("https://jobs.smartrecruiters.com/external-referrals/company/PublicisGroupe/publication/14156029-1abd-4d40-b2c4-8ddbad3b40d8?dcr_id=DCRA1")
                        .jobAd(JobAd.builder().sections(Sections.builder().companyDescription(Section.builder().title("Company Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, description</p>").build()).jobDescription(Section.builder()
                                        .title("Job Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>").build()).qualifications(Section.builder()
                                        .title("Qualifications")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>").build()).additionalInformation(Section.builder()
                                        .title("Additional Information")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>").build()).build())
                                .build()).department(Label.builder().id("977371").label("Data_Sciences").build()).build(),
                JobSR.builder()
                        .id("754568864546786").name("Data Analyst").uuid("b3222593-7e3f-48a4-b274-1451a6136ac0").jobAdId("126cde59-532a-4a0d-a013-33db7d17e3ef")
                        .refNumber("REF151278B").company(Company.builder().name("Publicis Groupe").identifier("PublicisGroupe").build())
                        .location(Location.builder().city("Bogota").country("co").address("Carrera 19a 90-12").remote(false).build())
                        .releasedDate("2022-01-12T09:48:42.000Z")
                        .postingUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referralUrl("https://jobs.smartrecruiters.com/external-referrals/company/PublicisGroupe/publication/b3222593-7e3f-48a4-b274-1451a6136ac0?dcr_id=DCRA1")
                        .jobAd(JobAd.builder().sections(Sections.builder().companyDescription(Section.builder().title("Company Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, description</p>").build()).jobDescription(Section.builder()
                                        .title("Job Description")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>").build()).qualifications(Section.builder()
                                        .title("Qualifications")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>").build()).additionalInformation(Section.builder()
                                        .title("Additional Information")
                                        .text("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>").build()).build())
                                .build()).department(Label.builder().id("977371").label("Data_Sciences").build()).build());

        List<Job> jobs = Arrays.asList(
                Job.builder()
                        .jobTitle("Junior Recruiter").srIdRawId("743999812085881").srUuid("e6123328-d104-495a-b624-6b828d92ddef").jobId("743999812085881-junior-recruiter")
                        .jobCategory("Human_Resources").postDate("2021-02-15T12:48:41.000Z").refNumber("REF151393D").country("Colombia").city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999812085881")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter")
                        .build(),
                Job.builder()
                        .jobTitle("Associate Data Scientist").srIdRawId("743999863425467").srUuid("14156029-1abd-4d40-b2c4-8ddbad3b40d8").jobId("743999863425467-associate-data-scientist")
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:55:54.000Z").refNumber("REF151364R").country("Colombia").city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999863425467")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist")
                        .build(),
                Job.builder()
                        .jobTitle("Data Analyst").srIdRawId("754568864546786").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac0").jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Colombia").city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build());

        Mockito.when(recruiterServiceMock.getIdJobs()).thenReturn(idsJobs);
        Mockito.when(recruiterServiceMock.getJobDetail(idsJobs)).thenReturn(jobsSr);
        Mockito.when(jobMapperMock.jobsSrToJob(jobsSr)).thenReturn(jobs);

        List<Job> jobList = cacheJobServiceImp.getJobListSr();

        Assertions.assertEquals(jobsSr.size(), jobList.size());
        Assertions.assertEquals(jobsSr.get(0).getId(), jobList.get(0).getSrIdRawId());
        Assertions.assertEquals(jobsSr.get(1).getId(), jobList.get(2).getSrIdRawId());
        Assertions.assertEquals(jobsSr.get(2).getId(), jobList.get(1).getSrIdRawId());
        Assertions.assertEquals("Colombia", jobList.get(0).getCountry());
    }

}
