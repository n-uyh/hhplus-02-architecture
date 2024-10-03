package com.hhplus.architecture.infra.lecture;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findAllByAppliedCntLessThanAndStartAtBetween(
        int maxCount, LocalDate from, LocalDate end
    );

    List<LectureEntity> findAllByIdIn(List<Long> ids);

    LectureEntity findOneById(long id);

    @Modifying
    @Query("update LectureEntity l set l.appliedCnt = :appliedCount where l.id = :id")
    int updateAppliedCount(@Param("id") long id, @Param("appliedCount") int appliedCount);
}
