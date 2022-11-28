package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> conditionCriteriaTypes;

    @Autowired
    private UserFieldCriteriaTypesImpl() {
        conditionCriteriaTypes = new HashMap<>();
        conditionCriteriaTypes.put("name", Boolean.TRUE);
        conditionCriteriaTypes.put("surname", Boolean.TRUE);
        conditionCriteriaTypes.put("patronymic", Boolean.TRUE);
        conditionCriteriaTypes.put("town", Boolean.TRUE);
        conditionCriteriaTypes.put("street", Boolean.TRUE);
        conditionCriteriaTypes.put("house", Boolean.TRUE);
        conditionCriteriaTypes.put("flat", Boolean.TRUE);
        conditionCriteriaTypes.put("bornDate", Boolean.TRUE);
        conditionCriteriaTypes.put("userRoles", Boolean.TRUE);
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
