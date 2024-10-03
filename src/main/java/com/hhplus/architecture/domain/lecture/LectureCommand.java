package com.hhplus.architecture.domain.lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LectureCommand {

    public record Search(LocalDate from, LocalDate end) {

        public LocalDateTime fromMinTime() {
            return LocalDateTime.of(from, LocalTime.MIN);
        }

        public LocalDateTime endMaxTime() {
            return LocalDateTime.of(end, LocalTime.MAX);
        }

    }

}
