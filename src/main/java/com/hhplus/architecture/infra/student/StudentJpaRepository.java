package com.hhplus.architecture.infra.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {

    StudentEntity findOneById(long studentId);

}
