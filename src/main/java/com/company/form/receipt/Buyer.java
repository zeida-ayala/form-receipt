package com.company.form.receipt;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class Buyer {
    public static final int MAX_LENGTH_FOR_NAME = 100;
    public static final int MAX_LENGTH_FOR_EMAIL = 50;
    public static final int MAX_LENGTH_FOR_ADDRESS = 50;

    @NotBlank(message="{not.blank.buyer.fullname}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message="{pattern.buyer.fullname}")
    @Size(max = MAX_LENGTH_FOR_NAME, message="{size.buyer.fullname}")
    private String fullName;

    @Valid
    private Ci ci;

    @Email(message = "{email.buyer.email}")
    @Size(max = MAX_LENGTH_FOR_EMAIL, message = "{size.buyer.email}")
    private String email;

    @Size(max = MAX_LENGTH_FOR_ADDRESS, message = "{size.buyer.address}")
    private String address;

    public String getFullName() {
        return fullName;
    }

    public Ci getCi() {
        return ci;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return  "     Buyer Details \n" +
                "           Full Name      " + fullName + "\n" +
                "           CI             " + ci + "\n" +
                "           Email          " + email + "\n" +
                "           Address        " + address + "\n";
    }
}
