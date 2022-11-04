package by.ilyin.core.repository.filtration;

import by.ilyin.core.entity.BaseEntity;
import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import by.ilyin.core.repository.filtration.specification.SearchCriteria;
import by.ilyin.core.repository.filtration.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

public class FiltrationBuilder<T extends BaseEntity> {

    private final SpecificationBuilder<T> currentSpecBuilder;

    private FiltrationBuilder() {
        this.currentSpecBuilder = new SpecificationBuilder<>();
    }

    public static FiltrationBuilder<? extends BaseEntity> getBuilder() {
        return new FiltrationBuilder<>();
    }

    public FiltrationBuilder<T> addCriteria(boolean isCorrectCondition, String fieldName, String operation, Object value) {
        if (isCorrectCondition) {
            addCriteria(fieldName, operation, value);
        }
        return this;
    }

    public Specification<T> build(FieldCriteriaTypes fieldCriteriaTypes) {
        return this.currentSpecBuilder.build(fieldCriteriaTypes);
    }

    private void addCriteria(String fieldName, String operation, Object value) {
        SearchCriteria<T> searchCriteria = new SearchCriteria<>(fieldName, operation, value);
        currentSpecBuilder.with(searchCriteria);
    }

}
