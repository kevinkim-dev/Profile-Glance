package com.profileglance.api.controller;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.api.service.LookatmeService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.db.entity.Lookatme;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value = "룩앳미 검색", notes = "<strong>카테고리</strong>를 통해 검색 한다.")
    public ResponseEntity<? extends BaseResponseBody> searchByCategory(@PathVariable("category") String category) {

        System.out.println("이것이 받은 카테고리 입니다~" + category);
        List<Lookatme> list = lookatmeService.searchByCategory(category);

        System.out.println(list.get(0).getLookatmeId());

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
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

}
