package com.hhplus.architecture.infra.lecture;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findAllByAppliedCntLessThanAndStartAtIsAfter(
        int maxCount, LocalDateTime dateTime
    );

    List<LectureEntity> findAllByIdIn(List<Long> ids);

    LectureEntity findOneById(long id);

    @Modifying
    @Query("update LectureEntity l set l.appliedCnt = :appliedCount where l.id = :id")
    int updateAppliedCount(@Param("id") long id, @Param("appliedCount") int appliedCount);
}
