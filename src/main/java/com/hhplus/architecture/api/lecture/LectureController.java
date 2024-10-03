package com.hhplus.architecture.api.lecture;

import com.hhplus.architecture.domain.lecture.LectureInfo;
import com.hhplus.architecture.domain.lecture.LectureService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture/")
public class LectureController {

    private final LectureService lectureService;

    /**
     * 신청 가능한 특강 목록 조회
     */
    @GetMapping("list")
    public ResponseEntity<List<LectureSearchDto.Response>> availableLectureList(
        LectureSearchDto.Request request
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
    public ResponseEntity<List<Object>> studentLecturesList(
        @PathVariable("studentId") long studentId
    ) {
        return null;
    }

    /**
     * 특강 신청
     */
    @PostMapping("regist")
    public ResponseEntity<Object> regist(
    ) {
        return null;
    }

}
