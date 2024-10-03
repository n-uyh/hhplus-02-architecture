package com.hhplus.architecture.domain.lectureRegistration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.hhplus.architecture.domain.lecture.Lecture;
import com.hhplus.architecture.domain.lecture.LectureRepository;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationCommand.Search;
import com.hhplus.architecture.domain.student.StudentInfo;
import com.hhplus.architecture.domain.student.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LectureRegistrationServiceTest {

    @InjectMocks
    private LectureRegistrationService lectureRegistrationService;

    @Mock
    private LectureRegistrationRepository registrationRepository;
    @Mock
    private LectureRepository lectureRepository;
    @Mock
    private StudentRepository studentRepository;

    @Test
    @DisplayName("사용자가 신청완료한 특강 목록 조회시 사용자 Id가 일치하고 목록의 사이즈가 일치한다")
    void findAllRegisteredLectures() {
        // given
        long studentId = 1L;
        Search searchCommand = new Search(studentId);
        List<Long> lectureIds = List.of(1L, 3L);

        when(studentRepository.findById(studentId)).thenReturn(
            new StudentInfo(studentId, "학생1")
        );

        when(registrationRepository.findAllLectureIdsByStudentId(studentId)).thenReturn(lectureIds);

        when(lectureRepository.findAllByIds(lectureIds)).thenReturn(
            List.of(
                new Lecture(lectureIds.get(0), "특강1", "강연자1", LocalDateTime.of(2024, 11, 10, 14, 0),
                    3),
                new Lecture(lectureIds.get(1), "특강2", "강연자3", LocalDateTime.of(2024, 11, 16, 14, 0),
                    27)
            )
        );

        // 로직 실행
        LectureRegistrationInfo result = lectureRegistrationService.findAllRegisteredLectures(
            searchCommand);
        // 검증
        // 1. 학생아이디 일치
        assertEquals(studentId, result.studentInfo().id());
        // 2. 특강 사이즈 일치
        assertEquals(2, result.lectureInfos().size());
    }

}
