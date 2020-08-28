package com.company.validators;

import com.company.form.components.Component;

import java.util.List;

public interface IComponentValidator <T> {

    boolean isValid(String strValue, Component<T> component);

}
