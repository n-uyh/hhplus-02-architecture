package com.hhplus.architecture.api.lecture;

import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationCommand;
import com.hhplus.architecture.domain.lectureRegistration.RegistrationCompleted;

public class LectureRegistDto {

    public record Request(
        long lectureId,
        long studentId
    ) {

        public LectureRegistrationCommand.Regist toCommand() {
            return new LectureRegistrationCommand.Regist(lectureId, studentId);
        }
    }

    public record Response(
        long studentId,
        CommonLectureResponse lecture
    ) {


        public static Response fromInfo(RegistrationCompleted completed) {
            LectureInfo lectureInfo = completed.lectureInfo();
            CommonLectureResponse lectureResponse = new CommonLectureResponse(
                lectureInfo.id(), lectureInfo.title(), lectureInfo.lecturerName(),
                lectureInfo.startAt());
            return new Response(completed.studentInfo().id(), lectureResponse);
        }
    }

}
