package com.hhplus.architecture.api.lecture;

import java.time.LocalDateTime;

public record CommonLectureResponse(
    long id,
    String title,
    String lecturerName,
    LocalDateTime startAt
) {

}
