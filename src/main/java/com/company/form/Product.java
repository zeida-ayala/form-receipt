package com.company.form;

import com.company.enums.Currency;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {

    @Size(min = 2, max = 100, message = "{size.product.description}")
    private String description;


    @NotNull(message = "{pattern.quantity.number}")
    private int quantity;

    private double unitPrice;

    private Currency currency;

    private Double total;

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getTotal() {
        return total;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void calculateTotalPrice() {
        total = quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "     Product Details" + "\n" +
                "          Product Description " + description + "\n" +
                "          Quantity            " + quantity + "\n" +
                "          Unit Price          " + unitPrice + "\n" +
                "          Currency            " + currency + "\n" +
                "          Total               " + total;
    }
}
