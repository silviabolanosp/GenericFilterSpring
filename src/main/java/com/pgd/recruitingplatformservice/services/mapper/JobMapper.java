package com.pgd.recruitingplatformservice.services.mapper;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.smartrecruiters.JobSR;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobMapper {

    String REGEX = "[^a-zA-Z 0-9]+";

    List<Job> jobsSrToJob(List<JobSR> jobsRS);

    @Mapping(source = "name", target = "jobTitle")
    @Mapping(source = "id", target = "srIdRawId")
    @Mapping(source = "uuid", target = "srUuid")
    @Mapping(source = ".", target = "jobId", qualifiedByName = "setUpJobId")
    @Mapping(source = "department.label", target = "jobCategory")
    @Mapping(source = "releasedDate", target = "postDate")
    @Mapping(source = "location.country", target = "country", qualifiedByName = "setUpJobCountry")
    @Mapping(source = "location.city", target = "city")
    @Mapping(source = "jobAd.sections.companyDescription.text", target = "overview")
    @Mapping(source = "jobAd.sections.qualifications.text", target = "qualifications")
    @Mapping(source = "jobAd.sections.jobDescription.text", target = "responsibilities")
    @Mapping(source = "jobAd.sections.additionalInformation.text", target = "benefits")
    @Mapping(source = "id", target = "referUrl", qualifiedByName = "setUpReferUrl")
    @Mapping(source = "postingUrl", target = "url")
    Job jobsSrToJob(JobSR jobRS);

    @Named("setUpJobId")
    static String setUpJobId(JobSR jobSR) {
        String jobName = jobSR.getName().replaceAll(REGEX, "").replace(" ", "-").toLowerCase();
        return jobSR.getId().concat("-").concat(jobName);
    }

    @Named("setUpJobCountry")
    static String setUpJobCountry(String locationCountry) {
        switch (locationCountry) {
            case "cr":
                locationCountry = "Costa Rica";
                break;
            case "co":
                locationCountry = "Colombia";
                break;
            case "ar":
                locationCountry = "Argentina";
                break;
            case "mx":
                locationCountry = "Mexico";
                break;
            default:
        }
        return locationCountry;
    }

    @Named("setUpReferUrl")
    static String setUpReferUrl(String id) {
        return "https://www.smartrecruiters.com/referrals-portal/navigation/posting/".concat(id);
    }

    List<JobEntity> jobsToJobsEntity(List<Job> jobs);

    List<Job> jobsEntityToJobs(List<JobEntity> jobEntities);
}
