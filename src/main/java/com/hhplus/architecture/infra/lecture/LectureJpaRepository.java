package com.hhplus.architecture.infra.lecture;

import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

    List<LectureEntity> findAllByAppliedCntLessThanAndStartAtBetween(
        int maxCount, LocalDateTime from, LocalDateTime end
    );

    List<LectureEntity> findAllByIdIn(List<Long> ids);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from LectureEntity l where l.id = :id")
    LectureEntity findOneByIdWithLock(@Param("id") long id);

    @Modifying
    @Query("update LectureEntity l set l.appliedCnt = :appliedCount where l.id = :id")
    int updateAppliedCount(@Param("id") long id, @Param("appliedCount") int appliedCount);
}
