package be.spring_flavyan.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ZipValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidZip {
    String message() default "Invalid ZIP code for the given country";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
