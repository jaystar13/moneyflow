package com.jaystar.moneyflow.common.exception;

public enum ErrorCode {
    CODE_NOT_FOUND(400, "C0_001", "코드를 찾을 수 없습니다."),

    CODE_TYPE_NOT_FOUND(400, "C0_002", "코드타입을 찾을 수 없습니다."),
    FINANCIAL_COMPANY_NOT_FOUND(400, "C0_003", "금융기관을 찾을 수 없습니다."),

    ACCOUNT_NOT_FOUND(400, "C0_004", "계좌를 찾을 수 없습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
