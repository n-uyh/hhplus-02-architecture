package com.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime from, LocalDateTime end);

    List<Lecture> findAllByIds(List<Long> ids);

    Lecture findOneByIdWithLock(long lectureId);

    int updateLectureAppliedCount(long id, int applied);
}
