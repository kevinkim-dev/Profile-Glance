package com.profileglance.api.controller;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.api.response.RecruitPostRes;
import com.profileglance.api.service.RecruitService;
import com.profileglance.api.service.RoomService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.db.entity.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "룩앳미 API", tags = {"Recruit"})
@RequestMapping("/recruit")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class RecruitController {

    @Autowired
    RecruitService recruitService;

    @Autowired
    RoomService roomService;

    // 채용 등록
    @PostMapping("/upload")
    @ApiOperation(value = "채용 등록", notes = "<strong>채용</strong>을 등록한다.")
    public ResponseEntity<? extends BaseResponseBody> uploadRecruit(@RequestBody RecruitPostReq recruitPostReq) {

        recruitService.uploadRecruit(recruitPostReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 회사 이름으로 검색
    @GetMapping("/searchByCompanyName/{companyName}")
    @ApiOperation(value = "회사 이름으로 검색하기", notes = "<strong>회사 이름 넣어주세요</strong>")
    public ResponseEntity<List<RecruitPostRes>> searchByCompanyName(@PathVariable("companyName") String companyName){

        return new ResponseEntity<List<RecruitPostRes>>(recruitService.searchByCompanyName(companyName), HttpStatus.OK);
    }

    // 직무 이름으로 필터링
    @GetMapping("/searchByJobName/{JobName}")
    @ApiOperation(value = "직무 이름으로 필터링", notes = "<strong>직무 이름 넣어주세요</strong>")
    public ResponseEntity<List<RecruitPostRes>> searchByJobName(@PathVariable("JobName") String JobName){

        return new ResponseEntity<List<RecruitPostRes>>(recruitService.searchByJobName(JobName), HttpStatus.OK);
    }

    // 채용 전체 리스트 출력
    @GetMapping("/searchAll")
    @ApiOperation(value = "채용 전체 리스트 출력", notes = "<strong>채용 전체 리스트를 출력합니다.</strong>")
    public ResponseEntity<List<RecruitPostRes>> searchAll() {

        return new ResponseEntity<List<RecruitPostRes>>(recruitService.searchAll(), HttpStatus.OK);
    }

    // 채용 삭제
    @DeleteMapping("/delete/{recruitId}")
    @ApiOperation(value = "채용 삭제", notes = "<strong>채용 아이디</strong>를 통해 채용을 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> delete(@PathVariable("recruitId") Long recruitId) {

        recruitService.deleteRecruit(recruitId);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 채용 방 생성
    @PostMapping("/createRoom")
    @ApiOperation(value = "채용 방 생성", notes = "<strong>채용 방을 생성한다.</strong>")
    public ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody String companyId) {

        Room room = roomService.createRoom(companyId, "re");

        recruitService.updateRecruit(room, companyId);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
}
