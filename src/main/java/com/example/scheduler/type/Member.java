package com.example.scheduler.type;

public enum Member {
    MEMBER("회원"),
    NOT_MEMBER("비회원");

    private String value;

    Member(String value) {
        this.value = value;
    }
}
