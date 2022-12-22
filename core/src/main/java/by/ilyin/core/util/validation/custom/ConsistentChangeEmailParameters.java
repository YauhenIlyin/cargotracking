package by.ilyin.core.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ChangeEmailParametersValidator.class)
@Target({METHOD, CONSTRUCTOR, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface ConsistentChangeEmailParameters {

    String message() default "Incorrect change email parameters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}