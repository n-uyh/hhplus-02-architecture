package com.hhplus.architecture.infra.lecture;

import com.hhplus.architecture.domain.lecture.Lecture;
import com.hhplus.architecture.domain.lecture.LectureRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository jpaRepository;

    /**
     * 신청가능한 특강 목록 조회
     *
     * @param maxCount   최대 수강인원
     * @param searchedAt 조회일시
     */
    @Override
    public List<Lecture> findAllLecturesAvailable(int maxCount, LocalDateTime searchedAt) {
        List<LectureEntity> availableLectureEntities = jpaRepository
            .findAllByAppliedCntLessThanAndStartAtIsAfter(maxCount, searchedAt);
        return availableLectureEntities.stream().map(LectureEntity::toDomain).toList();
    }

    @Override
    public List<Lecture> findAllByIds(List<Long> ids) {
        List<LectureEntity> entities = jpaRepository.findAllByIdIn(ids);
        return entities.stream().map(LectureEntity::toDomain).toList();
    }

    @Override
    public Lecture findOneById(long lectureId) {
        LectureEntity entity = jpaRepository.findOneById(lectureId);
        return Lecture.fromEntity(entity);
    }

    @Override
    public int updateLectureAppliedCount(long id, int applied) {
        return jpaRepository.updateAppliedCount(id, applied);
    }
}
