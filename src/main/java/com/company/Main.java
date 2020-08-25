package com.company;

import com.company.handlers.ReceiptHandler;

public class Main {

    public static void main(String[] args) {
        ReceiptHandler receiptHandler = new ReceiptHandler("secretreceipt");
        receiptHandler.setPathFile("src/output/receipt.json");
        receiptHandler.init();
    }
}

