package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class Location {
    /**
     * City of open position.
     */
    private String city;
    /**
     * Country of open position.
     */
    private String country;
    /**
     * Address of open position.
     */
    private String address;
    /**
     * Yes/No Remote.
     */
    private boolean remote;
}
