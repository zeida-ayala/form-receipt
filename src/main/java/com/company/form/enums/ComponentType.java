package com.company.form.enums;

public enum ComponentType {
    TEXT("Text"),
    TEXTNUMBER("Text Number"),
    TEXTPASSWORD("Text Password"),
    CHECKBOX("Checkbox"),
    RADIOBUTTON("Radio Button"),
    DROPDOWN("Dropdown"),
    DATE("Date"),
    SAVEBUTTON("Save Button");
    String name;
    ComponentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
