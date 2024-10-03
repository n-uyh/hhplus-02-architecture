package com.hhplus.architecture.domain.lectureRegistration;

import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.student.StudentInfo;
import java.util.List;

public record LectureRegistrationInfo(
    StudentInfo studentInfo,
    List<LectureInfo> lectureInfos
) {

}
