package by.ilyin.core.repository.filtration.specification;

import by.ilyin.core.entity.BaseEntity;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@NoArgsConstructor
public class SpecificationBuilder<T extends BaseEntity> {

    private final List<SearchCriteria<T>> params = new ArrayList<>();

    public SpecificationBuilder<T> with(String fieldName, String operation, Object value) {
        if (fieldName != null && operation != null && value != null) {
            params.add(new SearchCriteria<>(fieldName, operation, value));
        }
        return this;
    }

    public SpecificationBuilder<T> with(SearchCriteria<T> searchCriteria) {
        if (searchCriteria != null) {
            params.add(searchCriteria);
        }
        return this;
    }

    public Specification<T> build(FieldCriteriaTypes fieldCriteriaTypes) {
        Specification<T> result = null;
        if (params.size() > 0) {
            List<Specification<T>> specs = new ArrayList<>();
            for (SearchCriteria<T> currentCriteria : params) {
                specs.add(new CustomSpecification<>(currentCriteria));
            }
            String currentCriteriaName = params.get(0).getFieldName();
            boolean currentConditionIsAnd = fieldCriteriaTypes.fieldConditionIsAnd(currentCriteriaName);
            result = specs.get(0);
            for (int i = 1; i < params.size(); i++) {
                result = currentConditionIsAnd ?
                        Specification.where(result).and(specs.get(i)) :
                        Specification.where(result).or(specs.get(i));
                currentCriteriaName = params.get(i).getFieldName();
                currentConditionIsAnd = fieldCriteriaTypes.fieldConditionIsAnd(currentCriteriaName);
            }
        }
        return result;
    }

}
