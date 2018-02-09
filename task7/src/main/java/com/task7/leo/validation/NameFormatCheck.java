package com.task7.leo.validation;

import com.task7.leo.validation.Imp.NameFormatCheckImpl;
import com.task7.leo.validation.Imp.SymbolFormatCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameFormatCheckImpl.class)
@Documented
public @interface NameFormatCheck {
    String message() default "Fund name can only contain characters a-zA-Z";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
