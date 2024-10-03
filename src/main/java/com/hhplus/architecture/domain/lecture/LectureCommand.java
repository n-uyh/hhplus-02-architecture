package com.hhplus.architecture.domain.lecture;

import java.time.LocalDateTime;

public class LectureCommand {

    public record Search(LocalDateTime searchedAt) {

    }

}
