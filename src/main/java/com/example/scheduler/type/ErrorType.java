package com.example.scheduler.type;

public enum ErrorType{

    INTERNAL_SERVER_ERROR(500,"S001","서버에서 에러가 발생했습니다."),
    DUPLICATE_KET_ERROR(409,"C001","중복된 값이 존재 합니다."),
    NOT_USER_ERROR(403,"C002", "존재하지 않는 사용자 입니다."),
    BAD_REQUEST_ERROR(400,"C003", "잘못된 요청입니다."),
    NOT_FOUND_ERROR(404,"C004", "대상이 없습니다."),
    NO_CONTENT(204,"OK04", "콘텐츠 없음");

    private final String code;
    private final String errMsg;
    private int status;

    ErrorType(final int status, final String code, final String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public int getStatus() {
        return status;
    }
}


