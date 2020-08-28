package com.company.form.components;

import com.company.form.enums.ComponentType;
import com.company.validators.IComponentValidator;
import java.util.List;

public abstract class Component <T>{
    protected String title;
    protected ComponentType type;
    protected T value;
    protected IComponentValidator validator;
    private List<String> messagesValidation;

    public String getTitle(){
        return title;
    }

    public ComponentType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public IComponentValidator getValidator() {
        return validator;
    }

    public List<String> getMessagesValidation() {
        return messagesValidation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setValidator(IComponentValidator validator) {
        this.validator = validator;
    }

    public void setMessagesValidation(List<String> messagesValidation) {
        this.messagesValidation = messagesValidation;
    }

    public boolean isValid(String value) {
        return validator.isValid(value, this);
    }

    @Override
    public String toString() {
        return  title + ": " + value ;
    }
}
