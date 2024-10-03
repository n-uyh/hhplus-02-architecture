package com.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {

    List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime searchedAt);
}
