package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class Section {
    /**
     * String.
     */
    private String title;
    /**
     * String.
     */
    private String text;
}
