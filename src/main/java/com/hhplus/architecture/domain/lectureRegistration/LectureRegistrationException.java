package com.hhplus.architecture.domain.lectureRegistration;

import com.hhplus.architecture.support.BaseErrorCode;
import com.hhplus.architecture.support.BaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LectureRegistrationException extends BaseException {

    public LectureRegistrationException(RegistrationError errorCode) {
        super(errorCode);
    }

    @Getter
    @AllArgsConstructor
    public enum RegistrationError implements BaseErrorCode {
        NOT_FOUND(404, "LR001", "신청내역이 없습니다."),
        ALREADY_REGISTERED(400, "LR002", "이미 신청완료 하셨습니다.");

        private final int status;
        private final String code;
        private final String message;
    }
}
