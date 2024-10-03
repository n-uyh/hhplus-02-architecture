package com.hhplus.architecture.infra.student;

import com.hhplus.architecture.domain.student.StudentInfo;
import com.hhplus.architecture.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final StudentJpaRepository jpaRepository;

    @Override
    public StudentInfo findById(long studentId) {
        StudentEntity student = jpaRepository.findOneById(studentId);
        return student.toInfo();
    }
}
