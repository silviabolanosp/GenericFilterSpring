package com.pgd.recruitingplatformservice.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class ResponseApplyForJob {
    /**
     * A UUID data containing leadId send on request body or
     * assigned automatically by the service.
     */
    @Schema(description = "A UUID data containing leadId send on"
            + "request body or assigned automatically by the service.",
            example = "321f658c-bef0-4597-b1ff-b5e69c962edb")
    private UUID leadId;
    /**
     * Always New lead created.
     */
    @Schema(description = "Always New lead created",
            example = "New lead created")
    private String message;
    /**
     * Response from Smart Recruiter Platform.
     */
    @Schema(description = "Response from Smart Recruiter Platform."
            + "\n\nIf apply is registered message is like:"
            + "{id=anyData, createdOn=anyData, candidatePortalUrl=anyData,"
            + "code=null, message=null}"
            + "\n\nIf apply is NOT registered message is like:"
            + "{id=anyData, createdOn=null, candidatePortalUrl=null,"
            + "code=anyData, message=anyData}",
            example = "ResponseApplyForJobSr("
                    + "id=c70aec8b-2f7b-4adb-ae34-329d0838e63c,"
                    + "createdOn=2022-03-24T20:33:20+0000,"
                    + "candidatePortalUrl=https://my.smartrecruiters.com/"
                    + "public/sign-in?ftux=true,"
                    + "code=null,"
                    + "message=null)")
    private String smartRecruiters;
}
