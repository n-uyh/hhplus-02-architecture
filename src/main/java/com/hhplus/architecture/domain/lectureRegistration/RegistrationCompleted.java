package com.hhplus.architecture.domain.lectureRegistration;

import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.student.StudentInfo;

public record RegistrationCompleted(
    StudentInfo studentInfo,
    LectureInfo lectureInfo
) {

}
