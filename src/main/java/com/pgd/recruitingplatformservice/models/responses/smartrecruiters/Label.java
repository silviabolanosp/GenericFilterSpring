package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class Label {
    /**
     * String.
     */
    private String id;
    /**
     * String.
     */
    private String label;
}
