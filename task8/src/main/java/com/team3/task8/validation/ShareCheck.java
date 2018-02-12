package com.team3.task8.validation;


import com.team3.task8.validation.Impl.ShareCheckImpl;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ShareCheckImpl.class)
@Documented
public @interface ShareCheck {
    String message() default "share invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
