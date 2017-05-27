package com.project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.pojo.Supplier;
import com.project.pojo.User;

public class SupplierValidator implements Validator {
		
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		Supplier supplier = (Supplier) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "Username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "company", "error.invalid.user", "Company Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email","Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
}
}