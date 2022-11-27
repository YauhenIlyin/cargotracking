package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;

import java.util.HashMap;

public class ClientFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> conditionCriteriaTypes;

    private ClientFieldCriteriaTypesImpl() {
        conditionCriteriaTypes = new HashMap<>();
        conditionCriteriaTypes.put("name", Boolean.TRUE);
        conditionCriteriaTypes.put("status", Boolean.TRUE);
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
