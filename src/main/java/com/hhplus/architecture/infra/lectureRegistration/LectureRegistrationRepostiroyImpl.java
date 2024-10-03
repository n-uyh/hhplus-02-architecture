package com.hhplus.architecture.infra.lectureRegistration;

import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRegistrationRepostiroyImpl implements LectureRegistrationRepository {

    private final LectureRegistrationJpaRepository jpaRepository;

    @Override
    public List<Long> findAllLectureIdsByStudentId(long studentId) {
        return jpaRepository.findAllLectureIdsByStudentId(studentId);
    }

    @Override
    public void insertOne(long lectureId, long studentId) {
        LectureRegistrationEntity entity = LectureRegistrationEntity.builder()
            .lectureId(lectureId)
            .studentId(studentId)
            .build();

        jpaRepository.save(entity);
    }

    @Override
    public int countLectureRegistration(long lectureId, long studentId) {
        return jpaRepository.countByLectureIdAndStudentId(lectureId, studentId);
    }
}
