package ru.kors.specs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public enum SearchOperation {
        STARTING_WITH, ENDING_WITH, EQUAL, NOT_EQUAL,
        GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL,
        IN, NOT_IN
    }
}
