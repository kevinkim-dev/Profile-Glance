package com.profileglance.api.controller;

import com.profileglance.api.request.InterviewCheckPostReq;
import com.profileglance.api.request.InterviewPostReq;
import com.profileglance.api.request.InterviewRoomPostReq;
import com.profileglance.api.service.InterviewService;
import com.profileglance.api.service.RoomService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.db.entity.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "인터뷰 API", tags = {"Interview"})
@RequestMapping("/interview")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class InterviewController {

    @Autowired
    InterviewService interviewService;
    @Autowired
    RoomService roomService;

    @PostMapping("/createinterview")
    @ApiOperation(value = "인터뷰 추가!", notes = "기업이 유저에게 인터뷰 신청 버튼을 누르면 session_id =null 상태로 추가!")
    public ResponseEntity<? extends BaseResponseBody> createInterview(@RequestBody InterviewPostReq interviewPostReq) {
        if (interviewService.createInterview(interviewPostReq)) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(202, "성공"));
        } else
            return ResponseEntity.status(444).body(BaseResponseBody.of(444, "실패"));
    }

    @PostMapping("/createroom")
    @ApiOperation(value = "인터뷰 방 만들기", notes = "room을 생성하고 interview 테이블에 session_id 추가")
    public ResponseEntity<? extends BaseResponseBody> createInterviewRoom(@RequestBody InterviewRoomPostReq interviewRoomPostReq) {
        Room room = roomService.createRoom(interviewRoomPostReq.getCompanyId(), "in");
        interviewService.updateSesstion(room, interviewRoomPostReq.getUserNickname(), interviewRoomPostReq.getCompanyId());
        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "성공"));
    }

    @PostMapping("/checkCSID")
    @ApiOperation(value = "인터뷰 세션 있는지 확인", notes = "인터뷰 세션 있는지 확인")
    public ResponseEntity<? extends BaseResponseBody> checkCSID(@RequestBody InterviewCheckPostReq interviewCheckPostReq) {
        Boolean check = interviewService.checkCSID(interviewCheckPostReq.getUserNickname(), interviewCheckPostReq.getCsId());
        if(check) {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "있음"));
        } else {
            return ResponseEntity.status(202).body(BaseResponseBody.of(202, "없음"));
        }
    }

}
