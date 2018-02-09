package com.task7.leo.validation;


import com.task7.leo.validation.Imp.TransitionFundCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransitionFundCheckImpl.class)
@Documented
public @interface TransitionFundCheck {
    String message() default "Fund symbol not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
