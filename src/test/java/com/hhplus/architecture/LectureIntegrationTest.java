package com.hhplus.architecture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hhplus.architecture.domain.lecture.LectureException;
import com.hhplus.architecture.domain.lecture.LectureException.ErrorCode;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationCommand.Regist;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationException;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationException.RegistrationError;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationRepository;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class LectureIntegrationTest {

    @Autowired
    LectureRegistrationService lectureRegistrationService;

    @Autowired
    LectureRegistrationRepository registrationRepository;


    @Test
    @DisplayName("동시에 동일한 특강에 대해 40명이 신청했을 때, 30명만 성공한다")
    void registerConcurrent() throws InterruptedException {
        long lectureId = 2L;
        int count = 40;
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        CountDownLatch latch = new CountDownLatch(count);

        AtomicInteger errorCount = new AtomicInteger(0);
        for (int i = 0; i < count; i++) {
            long studentId = i + 1;

            executorService.submit(() -> {
                try {
                    lectureRegistrationService.regist(new Regist(lectureId, studentId));
                } catch (LectureException e) {
                    if (e.getErrorCode() == ErrorCode.OVER_MAX_STUDENT) {
                        errorCount.getAndAdd(1);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        assertEquals(10, errorCount.intValue());
    }

    @Test
    @DisplayName("동일한 사용자가 같은 특강을 5번 신청했을 때, 1번만 성공한다")
    void OneStudentTriesToRegistSameLecture5TimesButJust1Success() {
        long lectureId = 3L;
        long studentId = 1L;

        // 첫번째 케이스는 성공한다
        assertDoesNotThrow(
            () -> lectureRegistrationService.regist(new Regist(lectureId, studentId)));

        // 나머지 네건은 실패한다.
        for (int i = 0; i < 4; i++) {
            LectureRegistrationException exception = assertThrows(
                LectureRegistrationException.class,
                () -> lectureRegistrationService.regist(new Regist(lectureId, studentId))
            );
            assertEquals(RegistrationError.ALREADY_REGISTERED, exception.getErrorCode());
        }

        int registrationCount = registrationRepository.countLectureRegistration(lectureId,
            studentId);
        assertEquals(1, registrationCount);

    }
}
