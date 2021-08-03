package com.profileglance.api.controller;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.api.service.RecruitService;
import com.profileglance.common.response.BaseResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "룩앳미 API", tags = {"Recruit"})
@RequestMapping("/recruit")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class RecruitController {

    @Autowired
    RecruitService recruitService;

    // 채용 등록
    @PostMapping("/upload")
    @ApiOperation(value = "채용 등록", notes = "<strong>채용</strong>을 등록한다.")
    public ResponseEntity<? extends BaseResponseBody> uploadRecruit(@RequestBody RecruitPostReq recruitPostReq, @RequestParam String companyId) {

        recruitService.uploadRecruit(recruitPostReq, companyId);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }
}
