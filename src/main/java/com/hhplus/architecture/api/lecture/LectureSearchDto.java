package com.hhplus.architecture.api.lecture;

import com.hhplus.architecture.domain.lecture.LectureCommand;
import com.hhplus.architecture.domain.lecture.LectureCommand.Search;
import com.hhplus.architecture.domain.lecture.LectureInfo;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LectureSearchDto {

    public record Request(LocalDate from, LocalDate end) {


        public LectureCommand.Search toCommand() {
            return new Search(from, end);
        }
    }

    public record Response(
        long id,
        String title,
        String lecturerName,
        LocalDateTime startAt,
        int leftSeat // 남은 신청자 수
    ) {

        public static Response fromInfo(LectureInfo info) {
            return new Response(info.id(), info.title(), info.lecturerName(), info.startAt(),
                info.leftSeat());
        }
    }

}
