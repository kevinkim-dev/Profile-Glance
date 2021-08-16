package com.profileglance.api.controller;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.api.service.LookatmeService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.db.entity.Lookatme;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "룩앳미 API", tags = {"LookAtMe"})
@RequestMapping("/lookatme")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class LookatmeController {

    @Autowired
    LookatmeService lookatmeService;

    @PostMapping("/searchByCategory")
    @ApiOperation(value = "룩앳미 카테고리 검색", notes = "<strong>카테고리</strong>를 통해 검색 한다.")
    public ResponseEntity<List<LookatmePostRes>> searchByCategory(@RequestBody Map<String, String> category) {

        System.out.println("룩앳미 카테고리 검색 " + category.get("category"));
        List<LookatmePostRes> list = lookatmeService.searchByCategory(category.get("category"), Long.parseLong(category.get("limit")));

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "룩앳미 등록", notes = "<strong>룩앳미</strong>를 등록한다.")
    public ResponseEntity<? extends BaseResponseBody> uploadLookatme(@ModelAttribute LookatmePostReq lookatmePostReq) {

        System.out.println("룩앳미 등록 Controller");

        lookatmeService.uploadLookatme(lookatmePostReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @PutMapping("/update")
    @ApiOperation(value = "룩앳미 수정", notes = "<strong>룩앳미</strong>를 수정한다.")
    public ResponseEntity<? extends BaseResponseBody> updateLookatme(@ModelAttribute LookatmePostReq lookatmePostReq) {

        System.out.println("룩앳미 업데이트 Controller");

        lookatmeService.updateLookatme(lookatmePostReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @PostMapping("/searchByTitle")
    @ApiOperation(value = "룩앳미 제목 검색", notes = "<strong>재목</strong>를 통해 검색 한다.")
    public ResponseEntity<List<LookatmePostRes>> searchByTitle(@RequestBody Map<String, String> title) {

        System.out.println("룩앳미 제목 검색 " + title.get("title"));
        List<LookatmePostRes> list = lookatmeService.searchByTitle(title.get("title"), Long.parseLong(title.get("limit")));

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

    @PostMapping("/searchByNickname")
    @ApiOperation(value = "룩앳미 닉네임으로 검색", notes = "<strong>닉네임</strong>을 통해 검색 한다.")
    public ResponseEntity<List<LookatmePostRes>> searchByNickname(@RequestBody Map<String, String> nickname) {

        System.out.println("룩앳미 닉네임으로 검색 " + nickname.get("userNickname"));
        List<LookatmePostRes> list = lookatmeService.searchByNickname(nickname.get("userNickname"), Long.parseLong(nickname.get("limit")));

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

    @PostMapping("/orderByView")
    @ApiOperation(value = "룩앳미 조회순으로 정렬", notes = "<strong>조회수</strong>을 통해 정렬 한다.")
    public ResponseEntity<List<LookatmePostRes>> orderByView(@RequestBody Map<String, String> limit) {

        System.out.println("룩앳미 조회순 정렬 " + limit.get("limit"));
        List<LookatmePostRes> list = lookatmeService.orderByView( Long.parseLong(limit.get("limit")));

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

    // 룩엣미 상세보기
    @GetMapping("/detailLookatme/{lookatmeId}")
    @ApiOperation(value = "룩앳미 상세 보기", notes = "<strong>룩엣미 아이디</strong>를 통해 검색 한다.")
    public ResponseEntity<LookatmePostRes> detailLookatme(@PathVariable("lookatmeId") Long lookatmeId) {

        LookatmePostRes lookatmePostRes = lookatmeService.detailLookatme(lookatmeId);

        return new ResponseEntity<LookatmePostRes>(lookatmePostRes, HttpStatus.OK);
    }

    // 룩엣미 삭제하기
    @DeleteMapping("/deleteLookatme/{lookatmeId}")
    @ApiOperation(value = "룩앳미 삭제하기", notes = "<strong>룩엣미 아이디</strong>를 통해 삭제 한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteLookatme(@PathVariable("lookatmeId") Long lookatmeId) {

        System.out.println("룩앳미 삭제 Controller");

        Boolean check = lookatmeService.deleteLookatme(lookatmeId);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

}
