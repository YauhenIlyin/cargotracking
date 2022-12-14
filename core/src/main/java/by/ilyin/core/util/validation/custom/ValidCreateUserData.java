package by.ilyin.core.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserCreateParametersValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface ValidCreateUserData {

    String message() default "Incorrect user create parameters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
