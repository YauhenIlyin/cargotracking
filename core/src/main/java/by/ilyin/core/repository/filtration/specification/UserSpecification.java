package by.ilyin.core.repository.filtration.specification;

import by.ilyin.core.entity.CustomUser;
import by.ilyin.core.evidence.KeyWords;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

//todo generics
public class UserSpecification implements Specification<CustomUser> {

    private SearchCriteria criteria;

    public UserSpecification(SearchCriteria userSearchCriteria) {
        this.criteria = userSearchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<CustomUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate;
        String criteriaOperation = criteria.getOperation();
        switch (criteriaOperation) {
            case KeyWords.FILTER_OPERATION_MORE:
                predicate = builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getFieldName()), criteria.getValue().toString()
                );
                break;
            case KeyWords.FILTER_OPERATION_LESS:
                predicate = builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getFieldName()), criteria.getValue().toString()
                );
                break;
            case KeyWords.FILTER_OPERATION_EQUALS:
                if (root.get(criteria.getFieldName()).getJavaType() == String.class) {
                    predicate = builder.like(
                            root.get(criteria.getFieldName()), "%" + criteria.getValue() + "%");
                } else {
                    predicate = builder.equal(
                            root.get(criteria.getFieldName()), criteria.getValue());
                }
                break;
            case KeyWords.FILTER_OPERATION_EQUALS_SET_FIELD_ELEMENT:
                predicate = builder.isMember(criteria.getValue(), root.get(criteria.getFieldName()));
                break;
            default:
                predicate = null;
        }
        return predicate;
    }

}