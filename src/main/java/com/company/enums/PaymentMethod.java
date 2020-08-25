package com.company.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public enum PaymentMethod {

    CASH("Cash"),
    CREDITCARD("Credit Card");

    private static final List<String> options = new ArrayList<>();
    private static final Map<Integer, String> optionsMap = new HashMap<>();

    String name;

    static {
        AtomicInteger index = new AtomicInteger();
        int idOption = 0;
        for (PaymentMethod method : PaymentMethod.values()) {
            idOption = index.getAndIncrement() + 1;
            options.add(idOption + ". " + method.getName());
            optionsMap.put(idOption, method.getName());
        }
    }

    PaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<String> getOptions() {
        return options;
    }

    public static Map<Integer, String> getMapOptions() {
        return optionsMap;
    }

    @Override
    public String toString() {
        return name;
    }
}
