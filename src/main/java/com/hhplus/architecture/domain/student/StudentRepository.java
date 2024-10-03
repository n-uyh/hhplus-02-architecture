package com.hhplus.architecture.domain.student;

public interface StudentRepository {

    StudentInfo findById(long studentId);

}
