package com.hhplus.architecture.domain.lecture;

import java.time.LocalDate;
import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllLecturesAvailable(int maxCount, LocalDate from, LocalDate end);

    List<Lecture> findAllByIds(List<Long> ids);

    Lecture findOneById(long lectureId);

    int updateLectureAppliedCount(long id, int applied);
}
