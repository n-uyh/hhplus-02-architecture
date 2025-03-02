package com.hhplus.architecture.api.lecture;

import com.hhplus.architecture.api.lecture.LectureRegisteredDto.Request;
import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lecture.LectureService;
import com.hhplus.architecture.domain.lectureRegistration.LectureRegistrationService;
import com.hhplus.architecture.domain.lectureRegistration.LecturesRegisteredByStudent;
import com.hhplus.architecture.domain.lectureRegistration.RegistrationCompleted;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture/")
public class LectureController {

    private final LectureService lectureService;
    private final LectureRegistrationService lectureRegistrationService;

    /**
     * 신청 가능한 특강 목록 조회
     */
    @GetMapping("list")
    public ResponseEntity<List<LectureSearchDto.Response>> availableLectureList(
        @RequestBody LectureSearchDto.Request request
    ) {
        List<LectureInfo> lectureInfos = lectureService.findAllLecturesAvailable(
            request.toCommand());

        List<LectureSearchDto.Response> responses = lectureInfos.stream()
            .map(LectureSearchDto.Response::fromInfo).toList();

        return ResponseEntity.ok(responses);
    }

    /**
     * 학생이 신청완료한 특강 목록 조회
     */
    @GetMapping("list/{studentId}")
    public ResponseEntity<LectureRegisteredDto.Response> studentLecturesList(
        @PathVariable("studentId") long studentId
    ) {
        Request request = new LectureRegisteredDto.Request(studentId);
        LecturesRegisteredByStudent registionInfo = lectureRegistrationService.findAllRegisteredLectures(
            request.toCommand());

        return ResponseEntity.ok(LectureRegisteredDto.Response.fromInfo(registionInfo));
    }

    /**
     * 특강 신청
     */
    @PostMapping("regist")
    public ResponseEntity<LectureRegistDto.Response> regist(
        @RequestBody LectureRegistDto.Request request
    ) {
        RegistrationCompleted completed = lectureRegistrationService.regist(request.toCommand());

        return ResponseEntity.ok(LectureRegistDto.Response.fromInfo(completed));
    }

}
