package com.pgd.recruitingplatformservice.services;

import com.pgd.recruitingplatformservice.models.responses.StringResults;

import java.util.Optional;

public interface CategoriesService {
    Optional<StringResults> getCategoriesList();
}
