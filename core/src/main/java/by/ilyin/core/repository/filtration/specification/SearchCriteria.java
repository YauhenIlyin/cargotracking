package by.ilyin.core.repository.filtration.specification;

import by.ilyin.core.entity.BaseEntity;
import lombok.Data;

@Data
public class SearchCriteria<T extends BaseEntity> {

    private String fieldName;
    private String operation;
    private Object value;
    private boolean fieldConditionCriterionIsAnd = Boolean.FALSE;

    public SearchCriteria(String fieldName, String operation, Object value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
    }

}
