package com.profileglance.api.controller;

import com.profileglance.api.request.RoomInfoPostReq;
import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.api.service.RoomService;
import com.profileglance.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Room API", tags = {"Room"})
@RequestMapping("/room")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class RoomController {

    @Autowired
    RoomService roomService;

    // 면접 삭제
    @PostMapping("/deleteInterview")
    @ApiOperation(value = "면접 삭제", notes = "<strong>sessionId와 companyId</strong>를 통해 면접을 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteInterview(@RequestBody RoomDeleteReq roomReq) {

        roomService.deleteInterview(roomReq);
        roomService.deleteRoom(roomReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 채용 session id 삭제
    @PostMapping("/deleteRecruitSessionId")
    @ApiOperation(value = "채용 session id 삭제", notes = "<strong>sessionId와 companyId</strong>를 통해 면접을 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteRecruitSessionId(@RequestBody RoomDeleteReq roomReq) {

        roomService.deleteRecruitSessionId(roomReq);
        roomService.deleteRoom(roomReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // session_id로 room_category 검색
    @GetMapping("/findRoomCategory/{sessionId}")
    @ApiOperation(value = "session_id로 room_category 검색", notes = "session_id로 room_category 검색")
    public String findRoomCategory(@PathVariable String sessionId) {

        return roomService.findRoomCategory(sessionId);

    }

    // join시 room_info 테이블에 추가
    @PostMapping("/joinSession")
    @ApiOperation(value = "join시 room_info 테이블에 추가", notes = "join시 room_info 테이블에 추가")
    public ResponseEntity<? extends BaseResponseBody> joinSession(@RequestBody RoomInfoPostReq roomInfoPostReq){
        if (roomService.joinSession(roomInfoPostReq.getViewer(), roomInfoPostReq.getSessionId())){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "join 성공"));
        }else
            return ResponseEntity.status(488).body(BaseResponseBody.of(488, "join 실패"));
    }

    // leave시 room_info 테이블에서 삭제
    @PostMapping("/leaveSession")
    @ApiOperation(value = "leave시 room_info 테이블에서 삭제", notes = "leave시 room_info 테이블에서 삭제")
    public ResponseEntity<? extends BaseResponseBody> leaveSession(@RequestBody RoomInfoPostReq roomInfoPostReq){
        if (roomService.leaveSession(roomInfoPostReq.getViewer(), roomInfoPostReq.getSessionId())){
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "나가기성공"));
        }else
            return ResponseEntity.status(488).body(BaseResponseBody.of(488, "나가기실패"));
    }

    // session_id로 room_info 테이블에서 count
    @GetMapping("/countViewer/{sessionId}")
    @ApiOperation(value = "session_id로 room_info 테이블에서 count", notes = "session_id로 room_info 테이블에서 count")
    public Long countViewer(@PathVariable("sessionId") String sessionId){
        return roomService.countViewer(sessionId);
    }

    // session_id로 room create 시간 가져오기
    @GetMapping("/findRoomTime/{sessionId}")
    @ApiOperation(value = "session_id로 room create 시간 가져오기", notes = "session_id로 room create 시간 가져오기")
    public String findRoomTime(@PathVariable("sessionId") String sessionId){
        return roomService.findRoomTime(sessionId);
    }

}
