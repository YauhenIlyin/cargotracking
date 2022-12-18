package by.ilyin.core.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserUpdateParametersValidator.class)
@Target({METHOD, CONSTRUCTOR, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface ConsistentUpdateUserParameters {

    String message() default
            "Incorrect user update parameters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
