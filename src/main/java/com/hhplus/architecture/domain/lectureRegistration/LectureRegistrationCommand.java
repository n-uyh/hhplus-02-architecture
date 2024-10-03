package com.hhplus.architecture.domain.lectureRegistration;

public class LectureRegistrationCommand {

    public record Search(long studentId) {

    }

    public record Regist(long lectureId, long studentId) {

    }

}
