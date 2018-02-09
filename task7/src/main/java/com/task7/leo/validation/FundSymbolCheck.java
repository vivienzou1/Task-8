package com.task7.leo.validation;

import com.task7.leo.validation.Imp.FundSymbolCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FundSymbolCheckImpl.class)
@Documented
public @interface FundSymbolCheck {

    String message() default "Fund symbol doesn't exit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
