package by.ilyin.core.repository.filtration.specification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchCriteria {

    private String fieldName;
    private String operation;
    private Object value;
    private boolean isAndCriteria = Boolean.FALSE;

    public SearchCriteria(String fieldName, String operation, Object value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
    }

}