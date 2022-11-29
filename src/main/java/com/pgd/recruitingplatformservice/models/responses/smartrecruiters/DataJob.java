package com.pgd.recruitingplatformservice.models.responses.smartrecruiters;

import com.pgd.recruitingplatformservice.models.responses.ResumeJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class DataJob {
    /**
     * Minimal information about open position.
     */
    private List<ResumeJob> content;
    /**
     * Quantity of open position.
     */
    private Integer totalFound;
}
