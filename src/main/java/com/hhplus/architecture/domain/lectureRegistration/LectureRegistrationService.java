package com.hhplus.architecture.domain.lectureRegistration;

import com.hhplus.architecture.domain.lecture.Lecture;
import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lecture.LectureRepository;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationException.RegistrationError;
import com.hhplus.architecture.domain.student.StudentInfo;
import com.hhplus.architecture.domain.student.StudentRepository;
import java.time.LocalDateTime;
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
    public LecturesRegisteredByStudent findAllRegisteredLectures(
        LectureRegistrationCommand.Search command) {
        // 학생 조회
        StudentInfo studentInfo = studentRepository.findById(command.studentId());

        // 신청한 특강 아이디 리스트 조회
        List<Long> lectureIds = registrationRepository.findAllLectureIdsByStudentId(
            studentInfo.id());

        // 특강 조회
        List<Lecture> lectures = lectureRepository.findAllByIds(lectureIds);
        List<LectureInfo> lectureInfos = lectures.stream().map(LectureInfo::fromDomain).toList();

        return new LecturesRegisteredByStudent(studentInfo, lectureInfos);
    }

    @Transactional
    public RegistrationCompleted regist(LectureRegistrationCommand.Regist command) {
        // 기신청건 조회
        int count = registrationRepository.countLectureRegistration(command.lectureId(),
            command.studentId());
        if (count > 0) {
            throw new LectureRegistrationException(RegistrationError.ALREADY_REGISTERED);
        }

        // 학생조회
        StudentInfo studentInfo = studentRepository.findById(command.studentId());
        // 특강 조회
        Lecture lecture = lectureRepository.findOneById(command.lectureId());

        // 신청 가능한 상태인지 체크
        lecture.checkStudentCanRegist(LocalDateTime.now());

        // 신청 테이블 inset
        registrationRepository.insertOne(command.lectureId(), command.studentId());

        // 신청자수 update
        Lecture updatedLecture = lecture.addCount();
        lectureRepository.updateLectureAppliedCount(updatedLecture.id(),
            updatedLecture.appliedCnt());

        return new RegistrationCompleted(studentInfo, LectureInfo.fromDomain(updatedLecture));
    }

}
