package com.task7.leo.validation;


import com.task7.leo.validation.Imp.ZipcodeCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZipcodeCheckImpl.class)
@Documented
public @interface ZipcodeCheck {
    String message() default "Zipcode is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
