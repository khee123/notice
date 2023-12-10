package org.khaproject.notice.framwork;

public enum ResultCodeEnums {
    SUCCESS("200", "성공"),
    FAIL("999", "실패");

    private String code;
    private String message;

    ResultCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
