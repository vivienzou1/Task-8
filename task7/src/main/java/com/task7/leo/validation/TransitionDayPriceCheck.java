package com.task7.leo.validation;


import com.task7.leo.validation.Imp.TransitionDayPriceCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransitionDayPriceCheckImpl.class)
@Documented
public @interface TransitionDayPriceCheck {
    String message() default "fund price should be int range of 1 to 1000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
