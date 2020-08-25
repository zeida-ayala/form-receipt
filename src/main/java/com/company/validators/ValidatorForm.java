package com.company.validators;

import com.company.form.Ci;

import javax.validation.*;
import java.util.Set;

public class ValidatorForm {
    private ValidatorFactory factory;
    private Validator validator;
    private Set<? extends ConstraintViolation<?>> messages;

    public ValidatorForm() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Set<? extends ConstraintViolation<?>> getMessages() {
        return messages;
    }

    public boolean isValidString(Class clazz, String fieldName, String value) {
        boolean res = true;
        try {
            messages = validator.validateValue(clazz, fieldName, value);
            if (!messages.isEmpty()) {
                res = false;
            }
        } catch (ValidationException | NumberFormatException e) {
            res = false;
        }
        return res;
    }

    public boolean isValidInteger(Class clazz, String fieldName, int value) {
        boolean res = true;
        try {
            messages = validator.validateValue(clazz, fieldName, value);
            if (!messages.isEmpty()) {
                res = false;
            }
        } catch (ValidationException | NumberFormatException e) {
            res = false;
        }
        return res;
    }

    public boolean isValidDouble(Class clazz, String fieldName, double value) {
        boolean res = true;
        try {
            messages = validator.validateValue(clazz, fieldName, value);
            if (!messages.isEmpty()) {
                res = false;
            }
        } catch (ValidationException | NumberFormatException e) {
            res = false;
        }
        return res;
    }
}
