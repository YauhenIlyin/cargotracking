package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProductOwnerFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> conditionCriteriaTypes;

    private ProductOwnerFieldCriteriaTypesImpl() {
        conditionCriteriaTypes = new HashMap<>();
        conditionCriteriaTypes.put("name", Boolean.TRUE);
    }

    @Override
    public boolean fieldConditionIsAnd(String fieldName) {
        boolean result = Boolean.FALSE;
        if (conditionCriteriaTypes.containsKey(fieldName)) {
            result = conditionCriteriaTypes.get(fieldName);
        }
        return result;
    }

}
