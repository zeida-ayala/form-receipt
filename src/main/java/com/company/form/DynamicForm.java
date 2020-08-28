package com.company.form;

import com.company.form.components.Component;

import java.util.ArrayList;
import java.util.List;

public class DynamicForm {
    private String name;
    private List<Component<?>> components = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Component<?>> getComponents() {
        return components;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(List<Component<?>> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return name + "\n" + components;
    }
}
