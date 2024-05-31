package com.lux.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RussianPhoneNumberValidator.class)
public @interface RussianPhoneNumber {

    String message() default "Неверный формат российского номера телефона";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

