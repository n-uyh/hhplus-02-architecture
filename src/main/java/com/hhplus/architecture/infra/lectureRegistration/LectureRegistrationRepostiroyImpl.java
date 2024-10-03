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

}
