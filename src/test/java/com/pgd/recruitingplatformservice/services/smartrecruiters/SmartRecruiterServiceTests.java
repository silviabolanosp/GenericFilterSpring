package com.pgd.recruitingplatformservice.services.smartrecruiters;

import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.WebSr;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.ResponseApplyForJobSr;
import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.DataJob;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class SmartRecruiterServiceTests {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private SmartRecruiterServiceImp recruiterService;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(recruiterService, "baseUrl", "https://api.smartrecruiters.com/v1/companies/PublicisGroupe");
        ReflectionTestUtils.setField(recruiterService, "jobPostApplyUrl", "https://api.smartrecruiters.com/postings");
        ReflectionTestUtils.setField(recruiterService, "limit", 100);
        ReflectionTestUtils.setField(recruiterService, "country", "cr,co,ar,mx");
        ReflectionTestUtils.setField(recruiterService, "fielId", "59510507e4b073b05d32008a");
        ReflectionTestUtils.setField(recruiterService, "brand", "0d2d7cfa-9e5e-4390-a5cb-3002c1086f0b,aae4386c-7b0b-47c4-ac06-f3c5d4179622,default,4c527c8d-6d75-402b-a6c9-3df64f7e6e78");
        ReflectionTestUtils.setField(recruiterService, "smartToken", "DCRA1-646b1e67d0654a63912249277a0b6eed");
    }


    @Test
    public void getIdJobsTest() {
        DataJob dataJob = DataJob.builder()
                .content(Arrays.asList(ResumeJob.builder()
                        .releasedDate("2022-03-15T19:49:00.000Z")
                        .id("743999812211061")
                        .build(), ResumeJob.builder()
                        .releasedDate("2022-03-15T17:31:28.000Z")
                        .id("743999812173860")
                        .build()))
                .totalFound(2)
                .build();

        ResponseEntity<DataJob> response = ResponseEntity.ok(dataJob);

        Mockito.when(restTemplateMock.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class), ArgumentMatchers.eq(DataJob.class))).thenReturn(response);

        List<ResumeJob> idsJob = recruiterService.getIdJobs();

        assertFalse(idsJob.isEmpty());
        assertEquals(2, idsJob.size());
        assertEquals("743999812211061", idsJob.get(0).getId());
        assertEquals("743999812173860", idsJob.get(1).getId());

    }

    @Test
    public void getJobDetailTest() {
        List<ResumeJob> idsJobs = Arrays.asList(
                ResumeJob.builder().id("743999812211061").releasedDate("2022-03-15T19:49:00.000Z").build(),
                ResumeJob.builder().id("743999812173860").releasedDate("2022-03-15T17:31:28.000Z").build());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> job1 = ResponseEntity.ok("{'id': '743999812211061', 'name': 'Influencer Marketing'," +
                "  'uuid': 'e2b306cd-a784-4ca0-9f0c-7d2f5d655fc5'," +
                "  'jobAdId': '390032a4-9ed6-4d56-9fa5-08788b43229f'," +
                "   'refNumber': 'REF151449T', 'company': {'name': 'Publicis Groupe', 'identifier': 'PublicisGroupe'}," +
                "   'location': {'city': 'Heredia', 'region': 'Heredia', 'country': 'cr', 'address': 'Centro Corporativo Eurocenter', 'remote': false}," +
                "   'releasedDate': '2022-03-15T19:49:00.000Z', 'postingUrl': 'https://jobs.smartrecruiters.com/PublicisGroupe/743999812173860-influencer-marketing'," +
                "   'applyUrl': 'https://jobs.smartrecruiters.com/PublicisGroupe/743999812173860-influencer-marketing?oga=true'," +
                "   'referralUrl': 'https://jobs.smartrecruiters.com/external-referrals/company/PublicisGroupe/publication/e2b306cd-a784-4ca0-9f0c-7d2f5d655fc5?dcr_id=DCRA1'," +
                "   'jobAd': { 'sections': { 'companyDescription': { 'title': 'Descripción de la empresa', 'text': '<p>companyDescription</p>' }, " +
                "   'jobDescription': { 'title': 'Descripción del empleo', 'text': '<p>jobDescription</p>' }," +
                "   'qualifications': { 'title': 'Requisitos', 'text': '<p>qualifications</p>' }, " +
                "   'additionalInformation': {'title': 'Información adicional', 'text': '<p>additionalInformation</p>'} } }," +
                "    'department': {'id': 1090727, 'label': 'Creative'} }");

        ResponseEntity<String> job2 = ResponseEntity.ok("{'id': '743999812173860', 'name': '.NET Senior Software Engineer'," +
                "  'uuid': 'd6982c15-a700-4ba5-ba1a-190e37366191'," +
                "  'jobAdId': 'd6982c15-a700-4ba5-ba1a-190e37hgd56'," +
                "   'refNumber': 'REF151469V', 'company': {'name': 'Publicis Groupe', 'identifier': 'PublicisGroupe'}," +
                "   'location': {'city': 'Bogota', 'region': 'Bogota', 'country': 'co', 'address': 'Calle 22', 'remote': false}," +
                "   'releasedDate': '2022-03-15T17:31:28.000Z', 'postingUrl': 'https://jobs.smartrecruiters.com/PublicisGroupe/743999812173860-influencer-marketing'," +
                "   'applyUrl': 'https://jobs.smartrecruiters.com/PublicisGroupe/743999812211061--net-senior-software-engineer'," +
                "   'referralUrl': 'https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999812211061'," +
                "   'jobAd': { 'sections': { 'companyDescription': { 'title': 'Descripción de la empresa', 'text': '<p>companyDescription</p>' }, " +
                "   'jobDescription': { 'title': 'Descripción del empleo', 'text': '<p>jobDescription</p>' }," +
                "   'qualifications': { 'title': 'Requisitos', 'text': '<p>qualifications</p>' }, " +
                "   'additionalInformation': {'title': 'Información adicional', 'text': '<p>additionalInformation</p>'} } }," +
                "    'department': {'id': 1090727, 'label': 'Creative'} }");

        Mockito.when(restTemplateMock.exchange(ArgumentMatchers.matches("https://api.smartrecruiters.com/v1/companies/PublicisGroupe/postings/743999812211061"), ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.eq(request), ArgumentMatchers.eq(String.class))).thenReturn(job1);

        Mockito.when(restTemplateMock.exchange(ArgumentMatchers.matches("https://api.smartrecruiters.com/v1/companies/PublicisGroupe/postings/743999812173860"), ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.eq(request), ArgumentMatchers.eq(String.class))).thenReturn(job2);

        List<JobSR> jobsSr = recruiterService.getJobDetail(idsJobs);

        assertFalse(jobsSr.isEmpty());
        assertEquals(2, jobsSr.size());
        assertEquals("743999812211061", jobsSr.get(0).getId());
        assertEquals("2022-03-15T19:49:00.000Z", jobsSr.get(0).getReleasedDate());
        assertEquals("743999812173860", jobsSr.get(1).getId());
        assertEquals("2022-03-15T17:31:28.000Z", jobsSr.get(1).getReleasedDate());
    }

    @Test
    public void getJobDetailExceptionParsingJobTest() {
        List<ResumeJob> idsJobs = List.of(
                ResumeJob.builder().id("743999812211061").releasedDate("2022-03-15T19:49:00.000Z").build());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> job1 = ResponseEntity.ok("[{'id': '743999812211061', 'name': 'Influencer Marketing'}]");

        Mockito.when(restTemplateMock.exchange(ArgumentMatchers.matches("https://api.smartrecruiters.com/v1/companies/PublicisGroupe/postings/743999812211061"), ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.eq(request), ArgumentMatchers.eq(String.class))).thenReturn(job1);

        Throwable exception = assertThrows(JSONException.class, () -> recruiterService.getJobDetail(idsJobs));
        assertTrue(exception.getMessage().contains("JSONArray cannot be converted to JSONObject"));
    }

    @Test
    public void jobApplicationTest() {
        ApplyForJobLeadSr applyForJobLeadSr = ApplyForJobLeadSr.builder()
                .firstName("Testfirstname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("someAddress@someDomain.co")
                .phoneNumber("444-45454")
                .web(WebSr.builder().linkedinUrl("linedInd/test_DONT-USE").build())
                .build();

        ResponseEntity<String> responseOk =
                ResponseEntity.ok(
                        "{'id': '71e300c3-a189-4b8e-bf30-0630effd996b',"
                                + "'createdOn': '2022-03-18T15:10:41+0000',"
                                + "'candidatePortalUrl': 'https://my.smartrecruiters.com/public/sign-in?ftux=true'"
                                + "}"
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-SmartToken", "DCRA1-646b1e67d0654a63912249277a0b6eed");
        HttpEntity<Object> request = new HttpEntity<>(applyForJobLeadSr, headers);

        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.matches("https://api.smartrecruiters.com/postings/905f658c-bef0-4597-b1ff-b5e69c962edb/candidates"),
                        ArgumentMatchers.eq(HttpMethod.POST),
                        ArgumentMatchers.eq(request),
                        ArgumentMatchers.eq(String.class)))
                .thenReturn(responseOk);

        ResponseApplyForJobSr actualResult = recruiterService.applyForJobLead(applyForJobLeadSr, "905f658c-bef0-4597-b1ff-b5e69c962edb");

        assertEquals("71e300c3-a189-4b8e-bf30-0630effd996b", actualResult.getId());
        assertTrue(actualResult.getCandidatePortalUrl().contains("sign-in?ftux=true"));
        assertNull(actualResult.getMessage());
    }

    @Test
    public void jobApplicationWithWrongJobIdTest() {
        ApplyForJobLeadSr applyForJobLeadSr = ApplyForJobLeadSr.builder()
                .firstName("Testfirsttname_DONT-USE")
                .lastName("Testlastname_DONT-USE")
                .email("someAddress@someDomain.co")
                .phoneNumber("444-45454")
                .web(WebSr.builder().linkedinUrl("linedInd/test_DONT-USE").build())
                .build();

        ResponseEntity<String> response =
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(
                                "{'id': '2e730200-b389-4582-8114-b3f268579efe',"
                                        + "'code': 'RESOURCE_NOT_FOUND_ERROR',"
                                        + "'message': 'Not Found'"
                                        + "}"
                        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-SmartToken", "DCRA1-646b1e67d0654a63912249277a0b6eed");
        HttpEntity<Object> request = new HttpEntity<>(applyForJobLeadSr, headers);

        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.matches("https://api.smartrecruiters.com/postings/905f658c-bef0-4597-b1ff-b5e69c962zzz/candidates"),
                        ArgumentMatchers.eq(HttpMethod.POST),
                        ArgumentMatchers.eq(request),
                        ArgumentMatchers.eq(String.class)))
                .thenReturn(response);

        ResponseApplyForJobSr actualResult = recruiterService.applyForJobLead(applyForJobLeadSr, "905f658c-bef0-4597-b1ff-b5e69c962zzz");

        assertEquals("2e730200-b389-4582-8114-b3f268579efe", actualResult.getId());
        assertTrue(actualResult.getMessage().contains("Not Found"));
        assertNull(actualResult.getCandidatePortalUrl());
    }


}