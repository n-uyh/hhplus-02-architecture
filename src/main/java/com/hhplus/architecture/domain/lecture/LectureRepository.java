package com.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime searchedAt);

    List<Lecture> findAllByIds(List<Long> ids);

    Lecture findOneById(long lectureId);

    int updateLectureAppliedCount(long id, int applied);
}
