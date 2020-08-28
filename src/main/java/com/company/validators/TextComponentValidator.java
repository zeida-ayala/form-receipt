package com.company.validators;

import com.company.form.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextComponentValidator implements IComponentValidator<String> {
    protected String pattern;
    protected String messageInvalidValuePattern;
    protected Integer minLength;
    protected Integer maxLength;

    public String getPattern() {
        return pattern;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public boolean isValid(String strValue, Component<String> component ) {
        List<String> messagesValidation = new ArrayList<>();
        if (pattern != null) {
            Pattern patternValue = Pattern.compile(pattern);
            Matcher matcher = patternValue.matcher(strValue);
            if (!matcher.matches()) {
                messagesValidation.add(messageInvalidValuePattern);
                return false;
            }
        }
        if (minLength != null && strValue.length() < minLength) {
            messagesValidation.add(String.format("%s should have at least %s characters",
                    component.getTitle(), minLength));
            return false;
        }

        if (maxLength != null && strValue.length() < maxLength) {
            messagesValidation.add(String.format("%s should have as maximum %s characters",
                    component.getTitle(), maxLength));
            return false;
        }
        return true;
    }
}
