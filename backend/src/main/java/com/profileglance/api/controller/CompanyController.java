package com.profileglance.api.controller;

import com.profileglance.api.request.CompanyLoginPostReq;
import com.profileglance.api.service.CompanyService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.config.JwtTokenProvider;
import com.profileglance.db.entity.Company;
import com.profileglance.db.repository.CompanyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CompanyService companyService;

    // 회원가입
    @PostMapping("/signup")
    @ApiOperation(value = "기업회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    public ResponseEntity<? extends BaseResponseBody> signUp(@RequestBody Company company) {

        companyService.createCompany(company);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 기업회원 로그인
    @PostMapping("/login")
    @ApiOperation(value = "기업 회원 로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public String login(@RequestBody CompanyLoginPostReq companyLoginPostReq) {

        Company company = companyRepository.findByCompanyId(companyLoginPostReq.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원 입니다."));
        if (!passwordEncoder.matches(companyLoginPostReq.getCompanyPassword(), company.getCompanyPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(company.getCompanyId());
    }

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
