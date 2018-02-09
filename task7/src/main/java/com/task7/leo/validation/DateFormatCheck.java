package com.task7.leo.validation;


import com.task7.leo.validation.Imp.DateFormatCheckImpl;
import com.task7.leo.validation.Imp.TransitionDateCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatCheckImpl.class)
@Documented
public @interface DateFormatCheck {
    String message() default "Date invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
