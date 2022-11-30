package com.example.genericFilter.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
public class StringResults {
    /**
     * List of string for generic responses.
     */
    private List<String> results;
}
