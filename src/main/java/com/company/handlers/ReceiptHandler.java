package com.company.handlers;

import com.company.encriptors.AesEncryptDecrypt;
import com.company.encriptors.IEncryptDecrypt;
import com.company.enums.CiFrom;
import com.company.enums.Currency;
import com.company.enums.PaymentMethod;
import com.company.form.receipt.*;
import com.company.serializers.ISerializer;
import com.company.serializers.JsonSerializer;
import com.company.validators.ValidatorForm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReceiptHandler {

    private static final String MESSAGE_INVALID_VALUE = "Invalid value. Please enter again a value";
    private static final String MESSAGE_INVALID_OPTION = "Invalid option. Please enter an option again";
    private static final String MESSAGE_ERROR_SERIALIZE = "Serialization failed";
    private static final String MESSAGE_ERROR_DESERIALIZE = "Deserialization failed";
    private static final String MESSAGE_ERROR_ENCRYPT = "Encryption failed";
    private static final String MESSAGE_ERROR_DECRYPT = "Decryption failed";
    private static final String MESSAGE_SAVED_SUCCESSFULLY = "Receipt was stored successfully";

    private final Scanner scanner;
    private Receipt receipt;
    private ValidatorForm validator;
    private ISerializer serializer;
    private IEncryptDecrypt encryptDecrypt;
    private String pathFile;
    private String key;

    public ReceiptHandler(String key) {
        receipt = new Receipt();
        validator = new ValidatorForm();
        scanner = new Scanner(System.in);
        serializer = new JsonSerializer(new SimpleDateFormat("dd/MM/yyyy HH:mm a"));
        encryptDecrypt = new AesEncryptDecrypt();
        this.key = key;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public void init() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            String optionSelected = readLine();
            switch(optionSelected) {
                case "1":
                    fillReceipt();
                    break;
                case "2":
                    displayReceipt();
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void fillReceipt() {
        Buyer buyer = new Buyer();
        Ci ci = new Ci();
        Payment payment = new Payment();
        Product product = new Product();

        buyer.setFullName(readPropertyString(Buyer.class, "Full Name", "fullName"));
        ci.setNumber(readPropertyInteger(Ci.class, "CI", "number"));
        ci.setFrom(CiFrom.values()[readPropertyEnum(CiFrom.getOptions(), CiFrom.getMapOptions(), "Ci Extension")-1]);
        buyer.setEmail(readPropertyString(Buyer.class, "Email", "email"));
        buyer.setAddress(readPropertyString(Buyer.class, "Address", "address"));
        payment.setMethod(PaymentMethod.values()[readPropertyEnum(PaymentMethod.getOptions(), PaymentMethod.getMapOptions(),  "Payment Method")-1]);

        if (payment.getMethod().equals(PaymentMethod.CREDITCARD)) {
            payment.setCreditCardNumber(readPropertyString(Payment.class, "Credit Card Number", "creditCardNumber"));
        }

        product.setDescription(readPropertyString(Product.class, "Product Description", "description"));
        product.setQuantity(readPropertyInteger(Product.class, "Quantity", "quantity"));
        product.setUnitPrice(readPropertyDouble(Product.class, "Unit Price", "unitPrice"));
        product.setCurrency(Currency.values()[readPropertyEnum(Currency.getOptions(), Currency.getMapOptions(), "Currency")-1]);

        receipt.setBuyer(buyer);
        receipt.setPayment(payment);
        receipt.setProduct(product);
        receipt.setCreatedDate(new Date());
        if (saveReceipt()) {
            displayReceiptSummary();
        }
    }

    private void displayReceiptSummary() {
        printLine("Press 9 if you want to see the Receipt Summary");
        if (readLine().equals("9")) {
            displayReceipt();
        }
    }

    private boolean saveReceipt() {
        boolean saved = true;
        receipt.getProduct().calculateTotalPrice();
        try {
            if (receipt.getPayment().getCreditCardNumber() != null) {
                String cardEncrypted = encryptDecrypt.encrypt(receipt.getPayment().getCreditCardNumber(), key);
                receipt.getPayment().setCreditCardNumber(cardEncrypted);
            }
            serializer.write(pathFile, receipt);
            printLine(MESSAGE_SAVED_SUCCESSFULLY);
        } catch (IOException e) {
            saved = false;
            printLine(MESSAGE_ERROR_SERIALIZE + e.getMessage());
        } catch (Exception e){
            saved = false;
            printLine(MESSAGE_ERROR_ENCRYPT + e.getMessage());
        }
        return saved;
    }

    private void displayReceipt() {
        getReceipt();
        if (receipt != null) {
            printLine(receipt.toString());
        }

    }

    private void getReceipt() {
        receipt = null;
        try {
            receipt = serializer.read(pathFile, Receipt.class);
            if (receipt.getPayment().getCreditCardNumber() != null) {
                String decryptedCard = encryptDecrypt.decrypt(receipt.getPayment().getCreditCardNumber(), key);
                receipt.getPayment().setCreditCardNumber(decryptedCard);
            }
        } catch (IOException e) {
            printLine(MESSAGE_ERROR_DESERIALIZE + e.getMessage());
        } catch (Exception e) {
            printLine(MESSAGE_ERROR_DECRYPT + e.getMessage());
        }
    }

    private String readPropertyString(Class clazz, String propertyTitle, String propertyName) {
        String value;
        System.out.printf("Enter %s: ", propertyTitle);
        while (true) {
            value = readLine();
            if (validator.isValidString(clazz,  propertyName, value)) {
                break;
            } else {
                printMessagesValidator();
            }
        }
        return value;
    }

    private int readPropertyInteger(Class clazz, String propertyTitle, String propertyName) {
        int value;
        System.out.printf("Enter %s: ", propertyTitle);
        while (true) {
            try {
                value = Integer.valueOf(readLine());
                if (validator.isValidInteger(clazz, propertyName, value)) {
                    break;
                } else {
                    printMessagesValidator();
                }
            } catch (Exception e) {
                printMessagesValidator();
            }
        }
        return value;
    }

    private Double readPropertyDouble(Class clazz, String propertyTitle, String propertyName) {
        Double value;
        System.out.printf("Enter %s: ", propertyTitle);
        while (true) {
            try {
                value = Double.valueOf(readLine());
                if (validator.isValidDouble(clazz,  propertyName, value)) {
                    break;
                } else {
                    printMessagesValidator();
                }
            } catch (Exception e) {
                printMessagesValidator();
            }
        }
        return value;
    }

    private int readPropertyEnum(List<String> enumValues, Map<Integer,String> options, String titleProperty) {
        boolean isValid = false;
        int option = 0;
        while (!isValid) {
            System.out.println("Please select " + titleProperty);
            printList(enumValues);
            System.out.print("Option number: ");
            try {
                option = Integer.valueOf(readLine());
                if (options.get(option) != null) {
                    isValid = true;
                } else {
                    isValid = false;
                    System.out.println(MESSAGE_INVALID_OPTION);
                }
            } catch (NumberFormatException e) {
                isValid = false;
                System.out.println(MESSAGE_INVALID_VALUE);
            }
        }
        return option;
    }

    private void printList(List<String> options) {
        options.stream().forEach(System.out::println);
    }

    private void displayMainMenu() {
        printLine("Please enter a number option ");
        printLine("1 Fill receipt form");
        printLine("2 Print receipt form");
        printLine("3 Exit");
    }

    private void printLine(String line) {
        System.out.println(line);
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void printMessagesValidator() {
        if (!validator.getMessages().isEmpty()) {
            validator.getMessages().stream().forEach(m -> {
                printLine(m.getMessage());
            });
        } else {
            printLine(MESSAGE_INVALID_VALUE);
        }
    }
}
