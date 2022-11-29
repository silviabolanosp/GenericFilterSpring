package com.pgd.recruitingplatformservice.services.implementations;

import com.pgd.recruitingplatformservice.helpers.CacheHandler;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.models.responses.StringResults;
import com.pgd.recruitingplatformservice.services.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImp implements CountriesService {
    /**
     * Dependency injection of CacheHandler to extract countries from jobs.
     */
    @Autowired
    private CacheHandler cacheHandler;
    /**
     * Get list of countries extracted from job data cached.
     * @return optional string
     */
    @Override
    public Optional<StringResults> getCountriesList() {
        List<String> countries =
                cacheHandler
                        .getJobs()
                        .stream()
                        .map(Job::getCountry)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());
        return Optional
                .of(
                        StringResults
                                .builder()
                                .results(countries)
                                .build()
                );
    }
}
