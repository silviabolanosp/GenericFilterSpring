
package com.pgd.recruitingplatformservice.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class ApplyForJobLead {
    /**
     * A string containing first name of candidate.
     */
    @Schema(description = "A string containing first name of candidate",
            example = "firstnameTest_DontUse",
            required = true)
    @NonNull
    private String firstName;
    /**
     * A string containing last name of candidate.
     */
    @Schema(description = "A string containing last name of candidate",
            example = "lastnameTest_DontUse",
            required = true)
    @NonNull
    private String lastName;
    /**
     * A string containing email address of candidate.
     * Format is validated.
     */
    @Schema(description = "A string containing email address of candidate",
            example = "cr.careers.by.pdg@yopmail.com",
            required = true)
    @NonNull
    @Email
    private String email;
    /**
     * A string containing phone number of candidate.
     */
    @Schema(description = "A string containing phone number of candidate",
            example = "50683288274",
            required = true)
    @NonNull
    private String phoneNumber;
    /**
     * A string containing the name of the country.
     */
    @Schema(description = "A string containing the name of the country"
            + "of candidate.",
            example = "Costa Rica",
            required = true)
    @NonNull
    private String country;
    /**
     * A string containing LinkedIn information account.
     */
    @Schema(description = "LinkedIn information account.",
            example = "linedInd/test_DontUse",
            required = true)
    @NonNull
    private String linkedinUrl;
    /**
     * A string containing information of open position.
     * Format: leadId-positionName.
     */
    @Schema(description = "A string containing leadId-positionName of the"
            + "open position.",
            example = "743999812777904-senior-creative-engineer---e-mail",
            required = true)
    @NonNull
    private String jobId;
    /**
     * A string whit UUID associated to the open position.
     * Data is gotten of list of jobs.
     */
    @Schema(description = "UUID associated to the open position. Data is"
            + "gotten of list of jobs.",
            example = "905f658c-bef0-4597-b1ff-b5e69c962edb",
            required = true)
    @NonNull
    private String srUuid;
    /**
     * Optional Data.
     * It is provided automatically as a UUID random.
     */
    @Schema(description = "UUID format data. It is provided automatically as a"
            + "UUID random. It is not a mandatory data to send on request.",
            example = "321f658c-bef0-4597-b1ff-b5e69c962edb")
    @Builder.Default
    private UUID leadId = UUID.randomUUID();
    /**
     * Optional Data.
     * It is provided automatically as get time.
     */
    @Schema(description = "Time stamp of the request. It is provided by"
            + "Recruiting Platform. It is not a mandatory data to send"
            + "on request.",
            example = "2022-22-03T14:24:26.000Z")
    @Builder.Default
    private String timestamp = String.valueOf(new Date().getTime());
}
