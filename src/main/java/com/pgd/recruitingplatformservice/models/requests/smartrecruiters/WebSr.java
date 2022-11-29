package com.pgd.recruitingplatformservice.models.requests.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class WebSr {
    /**
     * A string containing LinkedIn information account.
     */
    private String linkedinUrl;
}
