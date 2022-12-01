package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class InvoiceFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> conditionCriteriaTypes;

    private InvoiceFieldCriteriaTypesImpl() {
        conditionCriteriaTypes = new HashMap<>();
        conditionCriteriaTypes.put("number", Boolean.TRUE);
        conditionCriteriaTypes.put("creationDate", Boolean.TRUE);
        conditionCriteriaTypes.put("verificationDate", Boolean.TRUE);
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
