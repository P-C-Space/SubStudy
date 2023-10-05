package com.nhnacademy;

public enum Item {
    APLLE("사과"),
    WATER("물"),
    CHILSUNGCIDER("사이다"),
    COCACOLA("콜라"),
    SNACK("과자"),
    BANANA("바나나");

    private String name;
    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
