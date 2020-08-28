package com.company.form.components;


import com.company.validators.TextComponentValidator;

public class Text extends Component<String>{

    public Text() {
        validator = new TextComponentValidator();
    }
}
