package com.example.genericFilter.controllers;

import com.example.genericFilter.models.responses.Person;
import com.pgd.genericFilter.models.requests.ApplyForPersonLead;
import com.example.genericFilter.models.responses.StringResults;
import com.example.genericFilter.services.PersonService;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RunWith(SpringRunner.class)
@WebMvcTest(value = RecruitingController.class)
public class RecruitingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService jobService;

    @MockBean
    private CountriesService countriesService;

    @MockBean
    private CategoriesService categoriesService;

    @Test
    public void getCountries_Ok() throws Exception{
        String url = "/api/v1/countries";

        List<String> listCountries =
                Arrays.asList(
                        "Colombia",
                        "Argentina"
                );

        Optional<StringResults> results =
                Optional.of(
                        StringResults
                                .builder()
                                .results(listCountries)
                                .build()
                );

        Mockito.when(countriesService.getCountriesList()).thenReturn(results);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void getCountries_InternalServerError() throws Exception{
        String url = "/api/v1/countries";

        Mockito.when(countriesService.getCountriesList()).thenThrow(HttpServerErrorException.InternalServerError.class);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void getCategories_Ok() throws Exception{
        String url = "/api/v1/categories";

        List<String> listCategories =
                Arrays.asList(
                        "Client_Management",
                        "Client_Management",
                        "Creative",
                        "Data_Sciences",
                        "Engineering",
                        "Finance",
                        "Production",
                        "Technology"
                );

        Optional<StringResults> results =
                Optional.of(
                        StringResults
                                .builder()
                                .results(listCategories)
                                .build()
                );

        Mockito.when(categoriesService.getCategoriesList()).thenReturn(results);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void getCategories_InternalServerError() throws Exception{
        String url = "/api/v1/categories";

        Mockito.when(categoriesService.getCategoriesList()).thenThrow(HttpServerErrorException.InternalServerError.class);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void getPersonList_Ok() throws Exception{
        String url = "/api/v1/jobs";

        Person jbo_result_1 =
                Person
                        .builder()
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999719516976-creative-designer?oga=true")
                        .benefits("<ul><li>Access to Prepaid Medical Plan provided by Prodigious</li><li>Employee engagement activities and events</li><li>Days of Work from Home</li><li>Flexible Schedule</li><li>Prodigious Academy<ul><li>Technical trainings, soft skills development, technical certifications, access to online libraries and e-learning platforms</li><li>English language training by a professional language teacher</li></ul></li><li>Find your path Program<ul><li>You will have a coach that will support and help you grow</li></ul></li><li>Level up Program<ul><li>Attend conferences, trainings and more (in COL or other countries)</li><li>Help others get better: conducting trainings and get rewarded for it</li></ul></li></ul>")
                        .city("Bogotá")
                        .country("Colombia")
                        .jobCategory("Creative")
                        .jobId("743999719516976-creative-designer")
                        .jobTitle("Creative Designer")
                        .overview("<p>Prodigious is a Publics Groupe’s production hub, Publicis is one of the largest communications groups in the world with over 80,0000 collaborators in over 100 countries. In Prodigious, we believe in the Power of One, for that we create an environment and a culture that allows high performing teams innovate and develop great experiences to our clients.<br /><br />We are looking for highly talented and enthusiastic professionals that are passionate about Design, Digital Marketing, Code Quality, Continuous Delivery and Continuous Improvement. You will have the opportunity to work together with our clients, in a distributed agile environment, where leadership is required but also built along a senior team.</p>")
                        .postDate("2020-09-14T13:23:07.000Z")
                        .qualifications("<ul><li>Agency experience preferred.</li><li>English B2 or higher is a MUST</li><li>Mastery of Adobe Creative Suite.</li><li>Portfolio that demonstrates the ability to fulfill the responsibilities of this role.</li><li>Knowledge of advertising idea development, process, design, and execution with the ability to work within brand guidelines.</li><li>Experience with photography, videography and content creation.</li><li>Excellent verbal and written communication skills; consistently maintains a high degree of professionalism when collaborating with colleagues and clients.</li><li>Exceptional organizational skills with an ability to prioritize projects and manage deadlines.</li><li>Understanding of best practices for print and digital production.</li><li>Self-motivated and proactive with a positive “can do” attitude.</li></ul><p><strong>Responsabilities:</strong></p><ul><li>Providing creative support to art directors, designers, account managers and copywriters.</li><li>Creating artwork in Photoshop or Illustrator in order to deliver comp art for presentations, digital and OOH.</li><li>Designing and/or modifying digital assets such as social / digital banners, HTML emails, landing pages, web design and other online assets.</li><li>Managing multiple job responsibilities and producing results independently as well as in collaboration with a team.</li><li>Ensuring creative deadlines are met while always maintaining brand integrity and a high standard of execution.</li><li>Preparing final files for digital production in various formats.</li></ul>")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999719516976")
                        .refNumber("REF19466G")
                        .responsibilities("<p>Looking for a creative designer to assist in creating, producing and maintaining visual assets for all client and agency deliverables. This could include print ads, digital banners, presentation materials, and other collateral materials.</p>")
                        .srIdRawId("743999719516976")
                        .srUuid("a76abded-f626-4934-9940-c94930a9c86b")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999719516976-creative-designer")
                        .priorityOrder(2)
                        .priorityValue("Low")
                        .build();

        Person jbo_result_2 =
                Person
                        .builder()
                        .applyUrl("https://jobs.smartrecruiters.com/PublicisGroupe/743999816380834--net-senior-software-engineer?oga=true")
                        .benefits("<p><strong>What you'll get!</strong></p><p>Access to Prepaid Medical Plan</p><p>Employee engagement activities and events</p><p>Work from Home</p><p>Flexible Schedules</p><p>Prodigious Academy</p><p>Technical trainings, soft skills development, technical certifications, access to online libraries and e-learning platforms</p><p>Wellness program</p><p>Career progression plans - Career path program</p>")
                        .city("San José")
                        .country("Costa Rica")
                        .jobCategory("Engineering")
                        .jobId("743999816380834-net-senior-software-engineer")
                        .jobTitle(".NET Senior Software Engineer")
                        .overview("<p>Publicis Global Delivery is the talent powerhouse of Publicis Groupe, the largest global communications group. We make sure to hire, boost and develop the best people worldwide to deliver outstanding work for the most prominent clients within the Groupe. </p><p>In LATAM, we are over 1,700 passionate employees that love to push boundaries and drive innovative solutions. If you are a risk-taker and love to develop intrepid ideas, PGD is the place for you.</p><p>We Move People, and People Move Us!</p>")
                        .postDate("2022-04-01T19:34:44.000Z")
                        .qualifications("<p><strong>What you'll do!</strong></p><ul><li>Work closely with Creative, Business, Technology and QA teams to ensure the technology will be functional and meet performance targets</li><li>Own the development of sub-systems and ensure they follow the established technical architecture and design</li><li>Implement user and business requirements which are composed of multiple development activities or touch various sub-systems</li><li>Maintain concise and clear documentation on projects as dictated by each capabilitiess guidelines and best practices</li><li>Debug complex issues in existing software applications</li><li>Provide proactive feedback on policies and procedures when an opportunity for improvement exists</li><li>Perform code reviews and enforce coding guidelines and best practice</li></ul><p><strong>What skills are needed for the role?</strong></p><ul><li>Bachelor's degree in Computer Science or related discipline, or equivalent practical experience overall</li><li>Between 3-5 years' software development experience working with .Net</li><li>Proficiency with data storage patterns and practices, namely relational (SQL) and/or non-relational (NoSQL) platforms</li><li>Proficiency in integrating web solutions with web services and third-party platforms</li><li>Knowledge of design patterns (e.g. model-view-controller, observer, etc.) and software engineering principles</li><li>Experience with Agile or Scrum software development methodologies</li><li>Capable of building a web solution based on a Content Management System or e-commerce platform</li><li>Capable of writing unit tests, and scripting load and performance tests</li><li>Capable of working with new technologies and building proof of concept prototypes</li><li>Capable of troubleshooting, debugging and doing root cause analysis of web platform issues</li><li>English level B2+ or higher</li></ul>")
                        .referUrl("https://www.smartrecruiters.com/referrals-portal/navigation/posting/743999816380834")
                        .refNumber("REF145197C")
                        .responsibilities("<p>We are looking for a Senior Software Engineer (.NET) with significant knowledge in REST API, Basic Javascript, HTML, and SQL specifically on store procedures and manipulation. Additionally, the Senior Software Engineer must have experience with cloud services in any platform, Pull request and Code review as well as with leading small teams of developers.</p>")
                        .srIdRawId("743999816380834")
                        .srUuid("12188563-7f69-4868-820e-bce13dd6bdaa")
                        .url("https://jobs.smartrecruiters.com/PublicisGroupe/743999816380834--net-senior-software-engineer")
                        .priorityOrder(2)
                        .priorityValue("Low")
                        .build();

        List<Person> jobs = List.of(jbo_result_1, jbo_result_2);

        Optional<List<Person>> results = Optional.of(jobs);

        Mockito.when(jobService.getPersonList()).thenReturn(results);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void getPersonListEmpty_Ok() throws Exception{
        String url = "/api/v1/jobs";

        List<Person> jobsEmpty = Collections.emptyList();

        Optional<List<Person>> resultsEmpty = Optional.of(jobsEmpty);

        Mockito.when(jobService.getPersonList()).thenReturn(resultsEmpty);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void getPersonList_InternalServerError() throws Exception{
        String url = "/api/v1/jobs";

        Mockito.when(jobService.getPersonList()).thenThrow(HttpServerErrorException.InternalServerError.class);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .get(url)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }

    @Test
    public void postPersonApplication_Ok() throws Exception{
        String url = "/api/v1/jobs-application";

        ResponseApplyForPerson responseApplyForPerson =
                ResponseApplyForPerson
                        .builder()
                        .leadId(UUID.fromString("9326cdca-ad2b-45ac-8327-51ec560f8662"))
                        .message("New lead created")
                        .smartRecruiters(""
                                + "ResponseApplyForPersonSr("
                                + "id=8cee448c-1e15-4848-b618-797db53fe7c7,"
                                + "createdOn=2022-03-22T20:35:27+0000,"
                                + "candidatePortalUrl=https://my.smartrecruiters.com/public/sign-in?ftux=true,"
                                + "code=null,"
                                + "message=null"
                                + ")"
                        )
                        .build();

        Optional<ResponseApplyForPerson> results = Optional.of(responseApplyForPerson);

        Mockito.when(jobService.applyForPersonLead(ArgumentMatchers.any(ApplyForPersonLead.class))).thenReturn(results);

        JSONObject applyForPersonLead = new JSONObject();

        applyForPersonLead.put("firstName", "Testfirstname_DONT-USE");
        applyForPersonLead.put("lastName", "Testlastname_DONT-USE");
        applyForPersonLead.put("email", "cr.careers.by.pdg@yopmail.com");
        applyForPersonLead.put("phoneNumber", "50683288274");
        applyForPersonLead.put("country", "Costa Rica");
        applyForPersonLead.put("jobId", "743999812777904-senior-creative-engineer---e-mail");
        applyForPersonLead.put("srUuid", "905f658c-bef0-4597-b1ff-b5e69c962edb");
        applyForPersonLead.put("linkedinUrl", "linedInd/test_DONT-USE");

        var jsonPost = applyForPersonLead.toString();

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void postPersonApplyment_InternalServerError() throws Exception{
        String url = "/api/v1/jobs-application";

        Mockito.when(jobService.applyForPersonLead(ArgumentMatchers.any(ApplyForPersonLead.class))).thenThrow(HttpServerErrorException.InternalServerError.class);

        JSONObject applyForPersonLead = new JSONObject();

        applyForPersonLead.put("firstName", "Testfirstname_DONT-USE");
        applyForPersonLead.put("lastName", "Testlastname_DONT-USE");
        applyForPersonLead.put("email", "cr.careers.by.pdg@yopmail.com");
        applyForPersonLead.put("phoneNumber", "50683288274");
        applyForPersonLead.put("country", "Costa Rica");
        applyForPersonLead.put("jobId", "743999812777904-senior-creative-engineer---e-mail");
        applyForPersonLead.put("srUuid", "905f658c-bef0-4597-b1ff-b5e69c962edb");
        applyForPersonLead.put("linkedinUrl", "linedInd/test_DONT-USE");

        var jsonPost = applyForPersonLead.toString();

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders
                        .post(url)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPost);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(500, result.getResponse().getStatus());
    }
}
