package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class WaybillFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> conditionCriteriaTypes;

    @Autowired
    private WaybillFieldCriteriaTypesImpl() {
        conditionCriteriaTypes = new HashMap<>();
        conditionCriteriaTypes.put("status", Boolean.FALSE);
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
