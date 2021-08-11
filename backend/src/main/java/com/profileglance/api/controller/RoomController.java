package com.profileglance.api.controller;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.api.service.RoomService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.db.entity.Interview;
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
    @DeleteMapping("/deleteInterview")
    @ApiOperation(value = "면접 삭제", notes = "<strong>sessionId와 companyId</strong>를 통해 면접을 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteInterview(@RequestBody RoomDeleteReq roomReq) {

        roomService.deleteInterview(roomReq);
        roomService.deleteRoom(roomReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 채용 session id 삭제
    @DeleteMapping("/deleteRecruitSessionId")
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
}
