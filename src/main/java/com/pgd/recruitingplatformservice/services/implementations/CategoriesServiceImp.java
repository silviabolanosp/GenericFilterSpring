package com.pgd.recruitingplatformservice.services.implementations;

import com.pgd.recruitingplatformservice.helpers.CacheHandler;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.StringResults;
import com.pgd.recruitingplatformservice.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImp implements CategoriesService {
    /**
     * Dependency injection of CacheHandler to extract categories from jobs.
     */
    @Autowired
    private CacheHandler cacheHandler;

    /**
     * Get list of categories extracted from job data cached.
     * @return optional string
     */
    @Override
    public Optional<StringResults> getCategoriesList() {
        List<String> categories =
                cacheHandler
                        .getJobs()
                        .stream()
                        .map(Job::getJobCategory)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
        return Optional
                .of(
                        StringResults
                                .builder()
                                .results(categories)
                                .build()
                );

    }
}
