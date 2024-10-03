package com.hhplus.architecture;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hhplus.architecture.domain.lecture.LectureException;
import com.hhplus.architecture.domain.lecture.LectureException.ErrorCode;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationCommand.Regist;
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
}
