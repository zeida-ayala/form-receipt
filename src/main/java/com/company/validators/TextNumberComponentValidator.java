package com.company.validators;

import com.company.form.components.Component;

public class TextNumberComponentValidator extends TextComponentValidator {
    private Integer minValue;
    private Integer maxValue;

    public Integer getMinValue() {
        return super.getMinLength();
    }

    public Integer getMaxValue() {
        return super.getMaxLength();
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean isValid(String strValue, Component<String> component ) {
        boolean res = super.isValid(strValue, component);
        Integer intValue =  Integer.valueOf(strValue);
        if (res && minValue != null && intValue >= minValue) {
            component.getMessagesValidation().add(String.format("%s should be at least %s",
                    component.getTitle(), minLength));
            return false;
        }

        if (res && maxValue != null && intValue <= maxValue) {
            component.getMessagesValidation().add(String.format("%s should be ",
                    component.getTitle(), maxLength));
            return false;
        }
        return res;
    }
}
