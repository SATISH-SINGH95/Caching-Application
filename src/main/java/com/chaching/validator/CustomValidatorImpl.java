package com.chaching.validator;



import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
public class CustomValidatorImpl implements CustomValidator {
    private final Validator validator;

    public <T> void validate(T object, Class<?>... groups) {
        if (object != null) {
            Set<ConstraintViolation<T>> violations = this.validator.validate(object, groups);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation Failed", violations);
            }
        }

    }
    public CustomValidatorImpl(final Validator validator) {
        this.validator = validator;
    }
}
