package com.pgd.recruitingplatformservice.services.mapper;

import com.pgd.recruitingplatformservice.models.requests.ApplyForJobLead;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.ApplyForJobLeadSr;
import com.pgd.recruitingplatformservice.models.requests.smartrecruiters.WebSr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ApplyJobMapper {

    @Mapping(
            source = "linkedinUrl",
            target = "web",
            qualifiedByName = "setUpLinkedinUrl"
    )
    ApplyForJobLeadSr
    applyForJobLeadToApplyForJobLeadSr(
            ApplyForJobLead applyForJobLead
    );

    @Named("setUpLinkedinUrl")
    static WebSr setUpLinkedinUrl(String linkedinUrl) {
        return WebSr.builder().linkedinUrl(linkedinUrl).build();
    }
}
