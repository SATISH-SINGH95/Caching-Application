package com.chaching.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.chaching.validator.UserStatusValidator;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserStatusValidator.class)
public @interface UserStatusAnnotation {

    public String message() default "User Status is not valid : It can be either Y or N";
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
