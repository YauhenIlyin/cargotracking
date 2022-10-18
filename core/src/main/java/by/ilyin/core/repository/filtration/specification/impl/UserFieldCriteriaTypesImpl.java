package by.ilyin.core.repository.filtration.specification.impl;

import by.ilyin.core.repository.filtration.specification.FieldCriteriaTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> isAndProjectCriteriaTypes;

    //todo how to store field names. String constants ?
    @Autowired
    private UserFieldCriteriaTypesImpl() {
        isAndProjectCriteriaTypes = new HashMap();
        isAndProjectCriteriaTypes.put("name", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("surname", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("patronymic", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("town", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("street", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("house", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("flat", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("beforeBornDate", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("afterBornDate", Boolean.TRUE);
        isAndProjectCriteriaTypes.put("userRoles", Boolean.TRUE);
    }

    public boolean isAndProjectCriteria(String fieldName) {
        boolean result = Boolean.FALSE;
        if (isAndProjectCriteriaTypes.containsKey(fieldName)) {
            result = isAndProjectCriteriaTypes.get(fieldName);
        }
        return result;
    }

}