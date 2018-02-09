package com.task7.leo.validation;


import com.task7.leo.validation.Imp.TransitionDateCheckImpl;
import com.task7.leo.validation.Imp.TransitionSymbolCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransitionDateCheckImpl.class)
@Documented
public @interface TransitionDateCheck {
    String message() default "Input date should be later than last transition date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
