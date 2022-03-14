package com.example.scheduler;

public enum TestEnum {
    CANCEL("구매의사 취소");

    private final String title;

    TestEnum(String title) {
        this.title = title;
    }

    public String getCode(){return name();};
}
