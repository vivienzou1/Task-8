package com.task7.leo.validation;

import com.task7.leo.validation.Imp.FundSymbolExistsCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FundSymbolExistsCheckImpl.class)
@Documented
public @interface FundSymbolExistsCheck {

    String message() default "Fund symbol already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
