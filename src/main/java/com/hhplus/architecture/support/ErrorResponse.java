package com.hhplus.architecture.support;

public record ErrorResponse(
    String code,
    String message
) {

    public static ErrorResponse error(BaseErrorCode code) {
        return new ErrorResponse(code.getCode(), code.getMessage());
    }
}
