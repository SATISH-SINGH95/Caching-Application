package com.chaching.validator;

public interface CustomValidator {
    <T> void validate(T object, Class<?>... groups);

    /*
    If you want to use this validator then you have to inject Dependency of CustomValidator and the call this validate method

    Like

    @Autowired
    private CustomValidator customValidator;

    customValidator.validate(yourDto_Object);
     */
}
