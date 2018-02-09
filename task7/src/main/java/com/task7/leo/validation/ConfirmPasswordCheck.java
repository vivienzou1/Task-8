package com.task7.leo.validation;

import com.task7.leo.validation.Imp.ConfirmPasswordCheckImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordCheckImpl.class)
@Documented
public @interface ConfirmPasswordCheck {
    String message() default "Password does not match the confirm password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
