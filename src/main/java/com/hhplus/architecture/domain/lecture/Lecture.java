package com.hhplus.architecture.domain.lecture;

import com.hhplus.architecture.domain.lecture.LectureException.ErrorCode;
import com.hhplus.architecture.infra.lecture.LectureEntity;
import java.time.LocalDateTime;

public record Lecture(
    long id,
    String title,
    String lecturerName,
    LocalDateTime startAt,
    int appliedCnt
) {

    /**
     * 최대수강인원
     */
    public static final int MAX_STUDENT = 30;

    /**
     * jpa entity -> domain
     */
    public static Lecture fromEntity(LectureEntity entity) {
        return new Lecture(entity.getId(), entity.getTitle(), entity.getLecturerName(),
            entity.getStartAt(), entity.getAppliedCnt());
    }

    /**
     * 특강의 잔여 좌석 수 계산
     */
    public int leftSeat() {
        return MAX_STUDENT - appliedCnt;
    }

    /**
     * 신청가능한지 체크
     */
    public void checkStudentCanRegist(LocalDateTime searchedAt) {
        if (startAt.isBefore(searchedAt)) {
            throw new LectureException(ErrorCode.PAST_START_AT);
        }
        if (leftSeat() <= 0) {
            throw new LectureException(ErrorCode.OVER_MAX_STUDENT);
        }
    }


    /**
     * 신청자수 + 1
     */
    public Lecture addCount() {
        int newCount = appliedCnt + 1;
        return new Lecture(id, title, lecturerName, startAt, newCount);
    }
}
