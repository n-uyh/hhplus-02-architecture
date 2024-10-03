package com.hhplus.architecture.domain.lectureRegistration;

import com.hhplus.architecture.domain.lecture.Lecture;
import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lecture.LectureRepository;
import com.hhplus.architecture.domain.student.StudentInfo;
import com.hhplus.architecture.domain.student.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureRegistrationService {

    private final LectureRegistrationRepository registrationRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;


    @Transactional(readOnly = true)
    public LectureRegistrationInfo findAllRegisteredLectures(
        LectureRegistrationCommand.Search command) {
        // 학생 조회
        StudentInfo studentInfo = studentRepository.findById(command.studentId());

        // 신청한 특강 아이디 리스트 조회
        List<Long> lectureIds = registrationRepository.findAllLectureIdsByStudentId(
            command.studentId());

        // 특강 조회
        List<Lecture> lectures = lectureRepository.findAllByIds(lectureIds);
        List<LectureInfo> lectureInfos = lectures.stream().map(LectureInfo::fromDomain).toList();

        return new LectureRegistrationInfo(studentInfo, lectureInfos);
    }
}
