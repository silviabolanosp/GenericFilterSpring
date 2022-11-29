package com.pgd.recruitingplatformservice.services;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.entities.ProcessEntity;
import com.pgd.recruitingplatformservice.models.requests.ApplyForJobLead;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.WebSr;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.ResponseApplyForJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.ResponseApplyForJobSr;
import com.pgd.recruitingplatformservice.repositories.JobEntityRepository;
import com.pgd.recruitingplatformservice.repositories.ProcessEntityRepository;
import com.pgd.recruitingplatformservice.services.implementations.JobServiceImp;
import com.pgd.recruitingplatformservice.services.mapper.ApplyJobMapper;
import com.pgd.recruitingplatformservice.services.mapper.JobMapperImpl;
import com.pgd.recruitingplatformservice.services.smartrecruiters.SmartRecruiterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

    @Mock
    private SmartRecruiterService recruiterServiceMock;

    @Mock
    private JobEntityRepository jobEntityRepositoryMock;

    @Mock
    private ProcessEntityRepository processEntityRepository;

    @Mock
    private JobMapperImpl jobMapperImpMock;

    @Mock
    private ApplyJobMapper applyJobMapperMock;

    @InjectMocks
    private JobServiceImp jobServiceImpMock;

    @Test
    public void getJobList_ok() {

        List<JobEntity> jobsEntity = Arrays.asList(
                JobEntity.builder()
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
                        .build(),
                JobEntity.builder()
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
                        .build(),
                JobEntity.builder()
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
                        .build(),
                JobEntity.builder()
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

        List<Job> jobs = Arrays.asList(
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
                        .build(),
                Job.builder()
                        .jobTitle("Associate Data Scientist")
                        .srIdRawId("743999863425467")
                        .srUuid("14156029-1abd-4d40-b2c4-8ddbad3b40d8")
                        .jobId("743999863425467-associate-data-scientist")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:55:54.000Z")
                        .refNumber("REF151364R")
                        .country("Colombia")
                        .city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999863425467")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist")
                        .build(),
                Job.builder()
                        .jobTitle("Data Analyst")
                        .srIdRawId("754568864546786")
                        .srUuid("b3222593-7e3f-48a4-b274-1451a6136ac0")
                        .jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences")
                        .postDate("2022-01-12T09:48:42.000Z")
                        .refNumber("REF151278B")
                        .country("Colombia")
                        .city("Bogota")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build(),
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

        Mockito.when(jobEntityRepositoryMock.findAll()).thenReturn(jobsEntity);
        //Mockito.when(processEntityRepository.save(new ProcessEntity(LocalDateTime.now()))).thenReturn(new ProcessEntity(LocalDateTime.now()));
        Mockito.when(jobMapperImpMock.jobsEntityToJobs(jobsEntity)).thenReturn(jobs);

        Optional<List<Job>> result = jobServiceImpMock.getJobList();

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(jobsEntity.size(), result.get().size());
        Assert.assertEquals(1, result.get().stream().filter(o -> o.getSrUuid().equals("e6123328-d104-495a-b624-6b828d92ddef")).count());
        Assert.assertEquals(1, result.get().stream().filter(o -> o.getSrUuid().equals("14156029-1abd-4d40-b2c4-8ddbad3b40d8")).count());
        Assert.assertEquals(1, result.get().stream().filter(o -> o.getSrUuid().equals("b3222593-7e3f-48a4-b274-1451a6136ac0")).count());
        Assert.assertEquals(1, result.get().stream().filter(o -> o.getSrUuid().equals("b3222593-7e3f-48a4-b274-1451a6136ac05")).count());

    }

    @Test
    public void applyForJob_ok() {

        ApplyForJobLead applyForJobLead = ApplyForJobLead.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .country("Costa Rica")
                .linkedinUrl("linedInd/test_DontUse")
                .jobId("743999812777904-senior-creative-engineer---e-mail")
                .srUuid("905f658c-bef0-4597-b1ff-b5e69c962edb")
                .build();

        ApplyForJobLeadSr applyForJobLeadSr = ApplyForJobLeadSr.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .web(new WebSr("linedInd/test_DontUse"))
                .build();

        ResponseApplyForJobSr responseApplyForJobSr = ResponseApplyForJobSr.builder()
                .id("8cee448c-1e15-4848-b618-797db53fe7c7")
                .createdOn("2022-03-22T20:35:27+0000")
                .candidatePortalUrl("https://my.smartrecruiters.com/public/sign-in?ftux=true")
                .code("null")
                .message("null")
                .build();

        Mockito.when(applyJobMapperMock.applyForJobLeadToApplyForJobLeadSr(ArgumentMatchers.any(ApplyForJobLead.class))).thenReturn(applyForJobLeadSr);
        Mockito.when(recruiterServiceMock.applyForJobLead(ArgumentMatchers.any(ApplyForJobLeadSr.class), ArgumentMatchers.any())).thenReturn(responseApplyForJobSr);
        Optional<ResponseApplyForJob> result = jobServiceImpMock.applyForJobLead(applyForJobLead);

        Assert.assertTrue(result.isPresent());
        Assert.assertNotNull(result.get().getLeadId());
        Assert.assertTrue(result.get().getSmartRecruiters().contains("https://my.smartrecruiters.com/public/sign-in?ftux=true"));
    }

    @Test
    public void applyForJob_WithTimeStampAndLeadId() {

        ApplyForJobLead applyForJobLead = ApplyForJobLead.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .country("Costa Rica")
                .linkedinUrl("linedInd/test_DontUse")
                .jobId("743999812777904-senior-creative-engineer---e-mail")
                .srUuid("905f658c-bef0-4597-b1ff-b5e69c962edb")
                .timestamp("2022-03-22T20:35:27+0000")
                .leadId(UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454"))
                .build();

        ApplyForJobLeadSr applyForJobLeadSr = ApplyForJobLeadSr.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .web(new WebSr("linedInd/test_DontUse"))
                .build();

        ResponseApplyForJobSr responseApplyForJobSr = ResponseApplyForJobSr.builder()
                .id("8cee448c-1e15-4848-b618-797db53fe7c7")
                .createdOn("2022-03-22T20:35:27+0000")
                .candidatePortalUrl("https://my.smartrecruiters.com/public/sign-in?ftux=true")
                .code("null")
                .message("null")
                .build();

        Mockito.when(applyJobMapperMock.applyForJobLeadToApplyForJobLeadSr(ArgumentMatchers.any(ApplyForJobLead.class))).thenReturn(applyForJobLeadSr);
        Mockito.when(recruiterServiceMock.applyForJobLead(ArgumentMatchers.any(ApplyForJobLeadSr.class), ArgumentMatchers.any())).thenReturn(responseApplyForJobSr);
        Optional<ResponseApplyForJob> result = jobServiceImpMock.applyForJobLead(applyForJobLead);

        Assert.assertTrue(result.isPresent());
        Assert.assertNotNull(result.get().getLeadId());
        Assert.assertTrue(result.get().getSmartRecruiters().contains("https://my.smartrecruiters.com/public/sign-in?ftux=true"));
    }

    @Test
    public void applyForJob_WrongSrUuid() {

        ApplyForJobLead applyForJobLead = ApplyForJobLead.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .country("Costa Rica")
                .linkedinUrl("linedInd/test_DontUse")
                .jobId("743999812777904-senior-creative-engineer---e-mail")
                .srUuid("905f658c-bef0-4597-b1ff-b5e69c962zzz")
                .build();

        ApplyForJobLeadSr applyForJobLeadSr = ApplyForJobLeadSr.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("cr.careers.by.pdg@yopmail.com")
                .phoneNumber("50683288274")
                .web(new WebSr("linedInd/test_DontUse"))
                .build();

        ResponseApplyForJobSr responseApplyForJobSr = ResponseApplyForJobSr.builder()
                .id("8cee448c-1e15-4848-b618-797db53fe7c7")
                .createdOn("null")
                .candidatePortalUrl("null")
                .code("RESOURCE_NOT_FOUND_ERROR")
                .message("Not Found")
                .build();

        Mockito.when(applyJobMapperMock.applyForJobLeadToApplyForJobLeadSr(ArgumentMatchers.any(ApplyForJobLead.class))).thenReturn(applyForJobLeadSr);
        Mockito.when(recruiterServiceMock.applyForJobLead(ArgumentMatchers.any(ApplyForJobLeadSr.class), ArgumentMatchers.any())).thenReturn(responseApplyForJobSr);
        Optional<ResponseApplyForJob> result = jobServiceImpMock.applyForJobLead(applyForJobLead);

        Assert.assertTrue(result.isPresent());
        Assert.assertNotNull(result.get().getLeadId());
        Assert.assertTrue(result.get().getSmartRecruiters().contains("RESOURCE_NOT_FOUND_ERROR"));
    }
}
