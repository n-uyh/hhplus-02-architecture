package com.hhplus.architecture.api.lecture;

import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationCommand;
import com.hhplus.architecture.domain.lectureRegistration.LecturesRegisteredByStudent;
import com.hhplus.architecture.domain.student.StudentInfo;
import java.util.List;

public class LectureRegisteredDto {

    public record Request(
        long studentId
    ) {

        public LectureRegistrationCommand.Search toCommand() {
            return new LectureRegistrationCommand.Search(studentId);
        }
    }

    public record Response(
        long studentId,
        List<CommonLectureResponse> lectures
    ) {

        public static Response fromInfo(LecturesRegisteredByStudent info) {

            StudentInfo studentInfo = info.studentInfo();

            List<CommonLectureResponse> lectures = info.lectureInfos().stream()
                .map(LectureInfo::toCommonResponse).toList();
            return new Response(studentInfo.id(), lectures);
        }
    }

}
