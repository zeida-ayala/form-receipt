package com.company.form.receipt;

import com.company.enums.PaymentMethod;

import javax.validation.constraints.Pattern;

public class Payment {

    private PaymentMethod method;

    @Pattern(regexp = "((?:(?:\\d{4}[- ]){3}\\d{4}|\\d{16}))(?![\\d])", message = "{pattern.payment.creditCardNumber}")
    private String creditCardNumber;

    public PaymentMethod getMethod() {
        return method;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "          Payment Method " +  method + "\n" +
                "              Credit Card Number " + convertCardToDisplay() + '\'' +
                '}';
    }

    private String convertCardToDisplay() {
        if (creditCardNumber == null) {
            return "";
        }
        return "xxxx - xxxx - xxxx - " + creditCardNumber.substring(12);
    }
}
