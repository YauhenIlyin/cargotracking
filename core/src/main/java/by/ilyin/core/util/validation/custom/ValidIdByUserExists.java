package by.ilyin.core.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserCreateParametersValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface ValidIdByUserExists {

    String message() default "Incorrect user parameters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}