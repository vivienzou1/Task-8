package com.task7.leo.validation;

import com.task7.leo.validation.Imp.SymbolFormatCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SymbolFormatCheckImpl.class)
@Documented
public @interface SymbolFormatCheck {
    String message() default "Fund symbol can only contain characters a-zA-Z";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
