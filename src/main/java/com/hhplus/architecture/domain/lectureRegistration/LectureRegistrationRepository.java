package com.hhplus.architecture.domain.lectureRegistration;

import java.util.List;

public interface LectureRegistrationRepository {

    List<Long> findAllLectureIdsByStudentId(long studentId);

}
