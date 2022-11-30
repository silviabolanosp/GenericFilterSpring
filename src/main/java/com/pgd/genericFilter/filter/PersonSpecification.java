package com.pgd.genericFilter.filter;


import com.pgd.genericFilter.entities.PersonEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Locale;

public class PersonSpecification implements Specification<PersonEntity> {

    private SpecSearchCriteria criteria;

    public PersonSpecification(final SpecSearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SpecSearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(final Root<PersonEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(builder.upper(root.get(criteria.getKey())), criteria.getValue().toString().toUpperCase());
            case STARTS_WITH:
                return builder.like(builder.upper(root.get(criteria.getKey())), criteria.getValue().toString().toUpperCase() + "%");
            case ENDS_WITH:
                return builder.like(builder.upper(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase());
            case CONTAINS:
                return builder.like(builder.upper(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase() + "%");
            default:
                return null;
        }
    }

}