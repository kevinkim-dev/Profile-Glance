package com.profileglance.api.controller;

import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.config.JwtTokenProvider;
import com.profileglance.db.repository.CompanyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "기업회원 API", tags = {"Company"})
@RequestMapping("/company")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class CompanyController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final CompanyRepository companyRepository;

    // 기업회원 아이디 중복 확인
    @GetMapping("/companyidcheck/{companyId}")
    @ApiOperation(value = "기업회원 아이디 중복 확인", notes = "DB에 있으면 202, 없으면 201")
    public ResponseEntity<? extends BaseResponseBody> userNicknameCheck(@PathVariable("companyId") String companyId){
        if (companyRepository.findByCompanyId(companyId).isPresent()){
            return ResponseEntity.status(202).body(BaseResponseBody.of(202, "아이디가 이미 있습니다."));
        }else {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "아이디 사용가능"));
        }
    }

}
