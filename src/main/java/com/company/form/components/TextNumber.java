package com.company.form.components;

import com.company.validators.TextNumberComponentValidator;

public class TextNumber  extends Text {
    public TextNumber() {
        validator = new TextNumberComponentValidator();
        //validator.setPattern("^-?[0-9]\\d*(\\.\\d*)?$");
    }
}
