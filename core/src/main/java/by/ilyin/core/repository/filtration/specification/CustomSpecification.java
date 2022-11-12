package by.ilyin.core.repository.filtration.specification;

import by.ilyin.core.entity.BaseEntity;
import by.ilyin.core.evidence.KeyWords;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.*;
import java.time.LocalDate;

@AllArgsConstructor
public class CustomSpecification<T extends BaseEntity> implements Specification<T> {

    private SearchCriteria<T> criteria;

    @Override
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {
        Predicate predicate = null;
        String criteriaOperation = criteria.getOperation();
        Class<?> currentFiledType = root.get(criteria.getFieldName()).getJavaType();
        switch (criteriaOperation) {
            case KeyWords.FILTER_OPERATION_MORE:
                if (currentFiledType == String.class) {
                    predicate = builder.greaterThanOrEqualTo(
                            root.get(criteria.getFieldName()), criteria.getValue().toString()
                    );
                } else if (currentFiledType == LocalDate.class) {
                    predicate = builder.greaterThanOrEqualTo(
                            root.get(criteria.getFieldName()), (LocalDate) criteria.getValue()
                    );
                } else {
                    //todo warning log
                }
                break;
            case KeyWords.FILTER_OPERATION_LESS:
                if (currentFiledType == String.class) {
                    predicate = builder.lessThanOrEqualTo(
                            root.get(criteria.getFieldName()), criteria.getValue().toString()
                    );
                } else if (currentFiledType == LocalDate.class) {
                    predicate = builder.lessThanOrEqualTo(
                            root.get(criteria.getFieldName()), (LocalDate) criteria.getValue()
                    );
                } else {
                    //todo warning log
                }
                break;
            case KeyWords.FILTER_OPERATION_EQUALS:
                if (currentFiledType == String.class) {
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
                //todo warning log
        }
        return predicate;
    }

}
