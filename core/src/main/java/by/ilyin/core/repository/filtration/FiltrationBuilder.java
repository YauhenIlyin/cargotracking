package by.ilyin.core.repository.filtration;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import by.ilyin.core.repository.filtration.specification.SearchCriteria;
import by.ilyin.core.repository.filtration.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class FiltrationBuilder {

    private SpecificationBuilder currentSpecBuilder;

    private FiltrationBuilder() {
        this.currentSpecBuilder = new SpecificationBuilder();
    }

    public static FiltrationBuilder getBuilder() {
        return new FiltrationBuilder();
    }

    public FiltrationBuilder addCriteria(boolean isCorrectCondition, String fieldName, String operation, Object value) {
        if (isCorrectCondition) {
            addCriteria(fieldName, operation, value);
        }
        return this;
    }

    public FiltrationBuilder addCriteria(boolean isCorrectCondition, String fieldName, String operation, String index, List list) {
        if (isCorrectCondition) {
            Object value = list.get(Integer.parseInt(index));
            addCriteria(fieldName, operation, value);
        }
        return this;
    }

    public Specification build(FieldCriteriaTypes fieldCriteriaTypes) {
        return this.currentSpecBuilder.build(fieldCriteriaTypes);
    }

    private void addCriteria(String fieldName, String operation, Object value) {
        SearchCriteria searchCriteria = new SearchCriteria(fieldName, operation, value);
        currentSpecBuilder.with(searchCriteria);
    }

}