package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.services.JobService;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CacheHandlerTests {

    @Mock
    private JobService jobServiceMock;

    @Mock
    private CacheManager cacheManager;

    @Mock
    private Cache cache;

    @Mock
    private JobMapper jobMapper;

    @Spy
    @InjectMocks
    private CacheHandler cacheHandler;

    @Test
    public void getJobsFromCacheTest() {
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(Job.builder()
                .jobTitle("Junior Recruiter").srIdRawId("743999812085881").srUuid("e6123328-d104-495a-b624-6b828d92ddef").jobId("743999812085881-junior-recruiter")
                .jobCategory("Human_Resources").postDate("2021-02-15T12:48:41.000Z").refNumber("REF151393D").country("Colombia").city("Bogota")
                .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter?oga=true")
                .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999812085881")
                .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812085881-junior-recruiter")
                .build());
        jobs.add(Job.builder()
                .jobTitle("Associate Data Scientist").srIdRawId("743999863425467").srUuid("14156029-1abd-4d40-b2c4-8ddbad3b40d8").jobId("743999863425467-associate-data-scientist")
                .jobCategory("Data_Sciences").postDate("2022-01-12T09:55:54.000Z").refNumber("REF151364R").country("Mexico").city("Cancun")
                .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist?oga=true")
                .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999863425467")
                .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999812061968-associate-data-scientist")
                .build());
        jobs.add(Job.builder()
                .jobTitle("Data Analyst").srIdRawId("754568864546786").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac0").jobId("754568864546786-data-analyst")
                .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Heredia")
                .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                .build());
        jobs.add(Job.builder()
                .jobTitle("Data Analyst 2").srIdRawId("7545688645467864").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05").jobId("754568864546786-data-analyst")
                .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Other city")
                .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                .build());

        SimpleValueWrapper wrapper = new SimpleValueWrapper(jobs);

        Mockito.when(cacheManager.getCache("jobsCache")).thenReturn(cache);
        Mockito.when(cache.get("jobs")).thenReturn(wrapper);
        Mockito.when(cache.get(ArgumentMatchers.any(), (Callable<Object>) ArgumentMatchers.any())).thenReturn(jobs);

        List<Job> results = cacheHandler.getJobs();

        assertEquals(4, results.size());
        assertEquals("743999812085881", results.get(0).getSrIdRawId());
        assertEquals("743999863425467", results.get(1).getSrIdRawId());
        assertEquals("754568864546786", results.get(2).getSrIdRawId());
        assertEquals("7545688645467864", results.get(3).getSrIdRawId());
    }

    @Test
    public void getJobsNoCacheTest() {
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:55:54.000Z").refNumber("REF151364R").country("Mexico").city("Cancun")
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Heredia")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build(),
                Job.builder()
                        .jobTitle("Data Analyst 2").srIdRawId("7545688645467864").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05").jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Other city")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build()
        );

        Optional<List<Job>> jobsList = Optional.of(jobs);

        Mockito.when(cacheManager.getCache("jobsCache")).thenReturn(null);
        Mockito.when(jobServiceMock.getJobList()).thenReturn(jobsList);

        List<Job> results = cacheHandler.getJobs();

        assertEquals(4, results.size());
        assertEquals("743999812085881", results.get(0).getSrIdRawId());
        assertEquals("743999863425467", results.get(1).getSrIdRawId());
        assertEquals("754568864546786", results.get(2).getSrIdRawId());
        assertEquals("7545688645467864", results.get(3).getSrIdRawId());
    }

    @Test
    public void updateCacheTest() {
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:55:54.000Z").refNumber("REF151364R").country("Mexico").city("Cancun")
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Heredia")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build(),
                Job.builder()
                        .jobTitle("Data Analyst 2").srIdRawId("7545688645467864").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05").jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Other city")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build()
        );

        List<JobEntity> jobsEntity = Arrays.asList(
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:55:54.000Z").refNumber("REF151364R").country("Mexico").city("Cancun")
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
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Heredia")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build(),
                JobEntity.builder()
                        .jobTitle("Data Analyst 2").srIdRawId("7545688645467864").srUuid("b3222593-7e3f-48a4-b274-1451a6136ac05").jobId("754568864546786-data-analyst")
                        .jobCategory("Data_Sciences").postDate("2022-01-12T09:48:42.000Z").refNumber("REF151278B").country("Costa Rica").city("Other city")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, description</p>")
                        .qualifications("<p>Prodigious is a Publics Groupe’s production hub, qualifications</p>")
                        .responsibilities("<p>Prodigious is a Publics Groupe’s production hub, jobDescription</p>")
                        .benefits("<p>Prodigious is a Publics Groupe’s production hub, additionalInformation</p>")
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst?oga=true")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/754568864546786")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999811964151-data-analyst")
                        .build()
        );

        Mockito.when(jobMapper.jobsEntityToJobs(jobsEntity)).thenReturn(jobs);
        Mockito.when(cacheManager.getCache("jobsCache")).thenReturn(cache);

        cacheHandler.updateCache(jobsEntity);
        Mockito.verify(cacheHandler, Mockito.times(1)).updateCache(jobsEntity);
    }

}
