package com.hhplus.architecture.domain.lecture;

import com.hhplus.architecture.support.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LectureException extends RuntimeException {

    private final ErrorCode errorCode;

    public LectureException(ErrorCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Getter
    @AllArgsConstructor
    public enum ErrorCode implements BaseErrorCode {
        NOT_FOUND(404, "L001", "강의를 찾을 수 없습니다."),
        OVER_MAX_STUDENT(409, "L002", "더이상 신청할 수 없습니다."),
        PAST_START_AT(400, "L003", "신청기간이 지났습니다.");

        private final int status;
        private final String code;
        private final String message;
    }
}
