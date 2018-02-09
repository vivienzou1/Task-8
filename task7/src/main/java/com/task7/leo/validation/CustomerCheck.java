package com.task7.leo.validation;

import com.task7.leo.validation.Imp.CustomerCheckImpl;
import com.task7.leo.validation.Imp.FundSymbolCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerCheckImpl.class)
@Documented
public @interface CustomerCheck {

    String message() default "Customer doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
