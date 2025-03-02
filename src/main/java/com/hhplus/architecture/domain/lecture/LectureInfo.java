package com.hhplus.architecture.domain.lecture;

import com.hhplus.architecture.api.lecture.CommonLectureResponse;
import java.time.LocalDateTime;

public record LectureInfo(
    long id,
    String title,
    String lecturerName,
    LocalDateTime startAt,
    int leftSeat // 남은 신청자 수
) {

    public static LectureInfo fromDomain(Lecture lecture) {
        return new LectureInfo(lecture.id(), lecture.title(), lecture.lecturerName(),
            lecture.startAt(),
            lecture.leftSeat());
    }

    public CommonLectureResponse toCommonResponse() {
        return new CommonLectureResponse(id, title, lecturerName, startAt);
    }

}
