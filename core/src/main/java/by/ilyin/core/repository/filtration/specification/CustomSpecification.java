package by.ilyin.core.repository.filtration.specification;

import by.ilyin.core.entity.BaseEntity;
import by.ilyin.core.evidence.KeyWords;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class CustomSpecification<T extends BaseEntity> implements Specification<T> {

    private SearchCriteria<T> criteria;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        Predicate predicate;
        String criteriaOperation = criteria.getOperation();
        switch (criteriaOperation) {
            case KeyWords.FILTER_OPERATION_MORE:
                predicate = builder.greaterThanOrEqualTo(
                        root.get(criteria.getFieldName()), criteria.getValue().toString()
                );
                break;
            case KeyWords.FILTER_OPERATION_LESS:
                predicate = builder.lessThanOrEqualTo(
                        root.get(criteria.getFieldName()), criteria.getValue().toString()
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
