package com.hhplus.architecture.infra.lectureRegistration;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureRegistrationJpaRepository extends
    JpaRepository<LectureRegistrationEntity, Long> {

    @Query("select r.lectureId from LectureRegistrationEntity r where r.studentId = :studentId")
    List<Long> findAllLectureIdsByStudentId(@Param("studentId") long studentId);

}
