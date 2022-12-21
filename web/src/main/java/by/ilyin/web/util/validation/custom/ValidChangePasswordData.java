package by.ilyin.web.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserChangePasswordParameterValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface ValidChangePasswordData {

    String message() default "Incorrect user create parameters.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
