package com.chaching.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.chaching.annotation.UserStatusAnnotation;

public class UserStatusValidator implements ConstraintValidator<UserStatusAnnotation, String> {

	@Override
	public boolean isValid(String userStatus, ConstraintValidatorContext context) {
		
		List<String> employeeTypes = Arrays.asList("Y", "N");
		
		return employeeTypes.contains(userStatus);
	}

}
