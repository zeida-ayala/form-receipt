package com.company.form.receipt;


import java.util.Date;

public class Receipt {
    private Buyer buyer;
    private Payment payment;
    private Product product;
    private Date createdDate;

    public Buyer getBuyer() {
        return buyer;
    }

    public Payment getPayment() {
        return payment;
    }

    public Product getProduct() {
        return product;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Receipt \n" +
                "     Created Date          " + createdDate + "\n" +
                buyer + "\n" +
                payment + "\n" +
                "Product Details \n" +
                product;
    }
}
