package com.team3.task8.validation;


import com.team3.task8.validation.Impl.CashCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CashCheckImpl.class)
@Documented
public @interface CashCheck {
    String message() default "cash invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
