package com.hhplus.architecture.infra.lecture;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findAllByAppliedCntLessThanAndStartAtIsAfter(
        int maxCount, LocalDateTime dateTime
    );

    List<LectureEntity> findAllByIdIn(List<Long> ids);
}
