package com.project.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.pojo.Product;
import com.project.pojo.User;

public class AddProductsValidator implements Validator {
	
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}
	public void validate(Object obj, Errors errors) {
		Product product = (Product) obj;
		
		if (!(product.getName().matches("^[a-zA-Z0-9]*$")))
            {
                errors.rejectValue("name", "name-invalid", "Name is not valid!");
            }
		
		else if (!(product.getCategory().matches("^[a-zA-Z0-9]*$")))
        {
            errors.rejectValue("category", "category-invalid", "Category is not valid!");
        }
		
		else if (!(product.getFilename().matches("^[a-zA-Z0-9]*$")))
        {
            errors.rejectValue("filename", "filename-invalid", "Filename is not valid!");
        }
	}
}
