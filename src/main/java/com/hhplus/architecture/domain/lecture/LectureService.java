package com.hhplus.architecture.domain.lecture;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    @Transactional(readOnly = true)
    public List<LectureInfo> findAllLecturesAvailable(
        LectureCommand.Search searchCommand
    ) {
        List<Lecture> availableLectures = lectureRepository.findAllLecturesAvailable(
            Lecture.MAX_STUDENT, searchCommand.searchedAt()
        );

        return availableLectures.stream().map(LectureInfo::fromDomain).toList();
    }
}
