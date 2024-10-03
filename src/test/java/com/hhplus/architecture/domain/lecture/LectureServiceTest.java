package com.hhplus.architecture.domain.lecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
    @DisplayName("신청 가능한 특강의 목록을 조회해왔을 때, 특강의 남은 좌석 수는 0보다 크고 특강의 시작일시는 검색일시 이후이다.")
    void findAllAvailableLectures() {
        // given
        LocalDateTime searchedAt = LocalDateTime.of(2024, 9, 30, 13, 0);

        LectureCommand.Search availableLectureCommand =
            new LectureCommand.Search(searchedAt); // 특정 시간에 들어온 명령 가정

        int maxStudent = Lecture.MAX_STUDENT;

        // expected
        LocalDateTime startAt = LocalDateTime.of(2024, 10, 9, 14, 0);
        int appliedCnt = 3;
        List<Lecture> expectedLectures = List.of(
            new Lecture(1, "특강1", "강연자1", startAt, appliedCnt)
        );

        when(lectureRepository.findAllLecturesAvailable(maxStudent, searchedAt))
            .thenReturn(expectedLectures);

        List<LectureInfo> results = lectureService.findAllLecturesAvailable(
            availableLectureCommand);

        // 검증
        assertEquals(1, results.size()); // list 사이즈 검증
        assertTrue(results.get(0).leftSeat() > 0); // 남은 좌석 수 검증
        assertTrue(results.get(0).startAt().isAfter(searchedAt)); // 시작시간이 검색시간 이후인지 검증
    }

}
