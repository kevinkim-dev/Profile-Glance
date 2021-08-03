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

@Api(value = "룩앳미 API", tags = {"LookAtMe"})
@RequestMapping("/lookatme")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class LookatmeController {

    @Autowired
    LookatmeService lookatmeService;

    @GetMapping("/searchByCategory/{category}")
    @ApiOperation(value = "룩앳미 카테고리 검색", notes = "<strong>카테고리</strong>를 통해 검색 한다.")
    public ResponseEntity<List<LookatmePostRes>> searchByCategory(@PathVariable("category") String category) {

        System.out.println("룩앳미 카테고리 검색 " + category);
        List<LookatmePostRes> list = lookatmeService.searchByCategory(category);

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    @ApiOperation(value = "룩앳미 등록", notes = "<strong>룩앳미</strong>를 등록한다.")
    public ResponseEntity<? extends BaseResponseBody> uploadLookatme(@RequestBody LookatmePostReq lookatmePostReq, @RequestParam String userEmail) {

        System.out.println("룩앳미 등록 Controller");
        lookatmeService.uploadLookatme(lookatmePostReq, userEmail);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @PostMapping("/update")
    @ApiOperation(value = "룩앳미 수정", notes = "<strong>룩앳미</strong>를 수정한다.")
    public ResponseEntity<? extends BaseResponseBody> updateLookatme(@RequestBody LookatmePostReq lookatmePostReq) {

        System.out.println("룩앳미 업데이트 Controller");

        lookatmeService.updateLookatme(lookatmePostReq);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    @GetMapping("/searchByTitle/{title}")
    @ApiOperation(value = "룩앳미 제목 검색", notes = "<strong>재목</strong>를 통해 검색 한다.")
    public ResponseEntity<List<LookatmePostRes>> searchByTitle(@PathVariable("title") String title) {

        System.out.println("룩앳미 제목 검색 " + title);
        List<LookatmePostRes> list = lookatmeService.searchByTitle(title);

        return new ResponseEntity<List<LookatmePostRes>>(list, HttpStatus.OK);
    }

}
