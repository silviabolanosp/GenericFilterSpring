package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.entities.ProcessEntity;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.repositories.JobEntityRepository;
import com.pgd.recruitingplatformservice.repositories.ProcessEntityRepository;
import com.pgd.recruitingplatformservice.services.CacheJobService;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JobSchedulerTests {

    @Mock
    private CacheJobService cacheJobService;

    @Mock
    private JobMapper jobMapper;

    @Mock
    private JobEntityRepository jobEntityRepository;

    @Mock
    private ProcessEntityRepository processEntityRepository;

    @Mock
    private CacheHandler cacheHandler;

    @Spy
    @InjectMocks
    private JobsScheduler jobsScheduler;

    @Test
    public void getJobsFromSmartRecruitersTest(){

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

        List<JobEntity> jobEntities = Arrays.asList(
                JobEntity.builder()
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
                JobEntity.builder()
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
                JobEntity.builder()
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

        Mockito.when(cacheJobService.getJobListSr()).thenReturn(jobs);
        Mockito.when(jobMapper.jobsToJobsEntity(jobs)).thenReturn(jobEntities);
        Mockito.when(jobEntityRepository.saveAll(jobEntities)).thenReturn(jobEntities);
        //Mockito.when(processEntityRepository.save(new ProcessEntity(LocalDateTime.now()))).thenReturn(new ProcessEntity(LocalDateTime.now()));

        jobsScheduler.getJobsFromSmartRecruiters();

        Mockito.verify(jobsScheduler, Mockito.times(1)).getJobsFromSmartRecruiters();

    }
}
