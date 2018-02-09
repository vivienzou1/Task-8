package com.task7.leo.validation;

import com.task7.leo.validation.Imp.StringSanitizerImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StringSanitizerImpl.class)
@Documented
public @interface StringSanitizer {

    String message() default "Input should not contain special characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
