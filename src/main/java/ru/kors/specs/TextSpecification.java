package ru.kors.specs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.kors.model.Text;

import java.util.ArrayList;
import java.util.List;

public class TextSpecification implements Specification<Text> {
    private final List<SearchCriteria> criteriaList = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<Text> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case EQUAL -> predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                case NOT_EQUAL -> predicates.add(criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                case STARTING_WITH -> predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        criteria.getValue().toString().toLowerCase() + "%"
                ));
                case ENDING_WITH -> predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase()
                ));

                case GREATER_THAN -> predicates.add(criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()
                ));
                case GREATER_THAN_EQUAL -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()
                ));
                case LESS_THAN -> predicates.add(criteriaBuilder.lessThan(
                        root.get(criteria.getKey()), criteria.getValue().toString()
                ));
                case LESS_THAN_EQUAL -> predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()
                ));
                case IN -> predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                case NOT_IN -> predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public boolean add(SearchCriteria criteria) {
        return criteriaList.add(criteria);
    }
}
