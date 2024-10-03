package com.hhplus.architecture.domain.lecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @Test
    @DisplayName("신청 가능한 특강의 목록을 조회해왔을 때, 특강의 남은 좌석 수는 0보다 크고 특강의 시작일시는 검색날짜 사이이다.")
    void findAllAvailableLectures() {
        // given
        LocalDate from = LocalDate.of(2024, 9, 10);
        LocalDate end = LocalDate.of(2024, 10, 2);
        LectureCommand.Search searchCommand = new LectureCommand.Search(from, end);
        int maxStudent = Lecture.MAX_STUDENT;

        // expected
        LocalDateTime startAt = LocalDateTime.of(2024, 9, 10, 14, 0);
        int appliedCnt = 3;
        List<Lecture> expectedLectures = List.of(
            new Lecture(1, "특강1", "강연자1", startAt, appliedCnt)
        );

        when(lectureRepository.findAllLecturesAvailable(maxStudent, searchCommand.fromMinTime(),
            searchCommand.endMaxTime()))
            .thenReturn(expectedLectures);

        List<LectureInfo> results = lectureService.findAllLecturesAvailable(
            searchCommand);

        // 검증
        assertEquals(1, results.size()); // list 사이즈 검증
        assertTrue(results.get(0).leftSeat() > 0); // 남은 좌석 수 검증
        assertTrue(results.get(0).startAt().isAfter(LocalDateTime.of(from, LocalTime.MIN)));
        assertTrue(results.get(0).startAt().isBefore(LocalDateTime.of(end, LocalTime.MAX)));
    }

}
