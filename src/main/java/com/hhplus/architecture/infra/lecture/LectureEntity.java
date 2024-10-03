package com.hhplus.architecture.infra.lecture;


import com.hhplus.architecture.domain.lecture.Lecture;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lecture")
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String lecturerName;
    private LocalDateTime startAt;
    private int appliedCnt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    /**
     * jpa entity -> domain
     */
    public Lecture toDomain() {
        return new Lecture(id, title, lecturerName, startAt, appliedCnt);
    }

}
