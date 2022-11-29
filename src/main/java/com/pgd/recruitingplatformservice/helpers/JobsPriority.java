package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.models.responses.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The class is final as suggested by CheckStyle.
 * To do unit testing and mock the class it is necessary to add text file:
 * src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker
 */
@Component
public final class JobsPriority {
    private JobsPriority() { }

    /**
     * Guarantee priority Order and Value to sort data.
     * @param jobs list of jobs to set priority Value and Order.
     * @return List<Job>
     */
    public static List<Job> validatePriorityOrderAndPriorityValue(
            final List<Job> jobs
    ) {
        return jobs
                .parallelStream()
                .map(
                        job -> job
                                .toBuilder()
                                .priorityValue(getUpPriorityValue(job))
                                .priorityOrder(getUpPriorityOrder(job))
                                .build()
                )
                .collect(Collectors.toList());
    }

    private static String getUpPriorityValue(
            final Job job
    ) {
        String priorityValue =
                null;
        if (job.getPriorityValue() == null && job.getPriority() != null) {
            priorityValue =
                    job.getPriority();
        } else if (job.getPriorityValue() == null) {
            priorityValue = "Low";
        }
        return priorityValue;
    }

    private static Integer getUpPriorityOrder(
            final Job job
    ) {
        int priorityOrder = 0;
        if (job.getPriorityOrder() == null) {
            String priorityValue =
                    getUpPriorityValue(job);
            if (priorityValue != null) {
                switch (priorityValue) {
                    case "Normal":
                        priorityOrder = 1;
                        break;
                    case "Low":
                        priorityOrder = 2;
                        break;
                    default:
                }
            }
        }
        return priorityOrder;
    }
}
