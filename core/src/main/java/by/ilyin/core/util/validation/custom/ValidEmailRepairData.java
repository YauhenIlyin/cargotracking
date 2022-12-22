package by.ilyin.core.util.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = EmailRepairParameterDataValidator.class)
@Target(PARAMETER)
@Retention(RUNTIME)
@Documented
public @interface ValidEmailRepairData {

    String message() default "Incorrect email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
