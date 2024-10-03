package com.hhplus.architecture.domain.lecture;

import java.time.LocalDate;

public class LectureCommand {

    public record Search(LocalDate from, LocalDate end) {

    }

}
