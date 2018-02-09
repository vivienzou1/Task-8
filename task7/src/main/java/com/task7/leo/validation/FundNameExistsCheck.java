package com.task7.leo.validation;

import com.task7.leo.validation.Imp.FundNameExistsCheckImpl;
import com.task7.leo.validation.Imp.FundSymbolCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FundNameExistsCheckImpl.class)
@Documented
public @interface FundNameExistsCheck {

    String message() default "Fund name already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
