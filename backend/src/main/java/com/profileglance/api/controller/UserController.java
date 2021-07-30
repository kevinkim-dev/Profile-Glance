package com.profileglance.api.controller;


import com.profileglance.api.request.UserLoginPostReq;
import com.profileglance.api.service.UserService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.config.JwtTokenProvider;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Api(value = "유저 API", tags = {"User"})
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    // 회원가입
    @PostMapping("/signup")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    public ResponseEntity<? extends BaseResponseBody> join(@RequestBody User user) {

        userService.createUser(user);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 로그인
    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    public String login(@RequestBody UserLoginPostReq userLoginPostReq) {
        User member = userRepository.findByUserEmail(userLoginPostReq.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(userLoginPostReq.getUserPassword(), member.getUserPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUserEmail());
    }

    // 사진 업로드
    @PostMapping("/uploadImg")
    @ApiOperation(value = "사진 업로드", notes = "<strong>파일 경로</strong>로 업로드한다.", produces = "multipart/form-date")
    public ResponseEntity<? extends BaseResponseBody> uploadUserImg(@RequestPart("userImg") MultipartFile files, @RequestParam("userEmail")String userEmail) {
        try {
            String baseDir = "C:\\Users\\multicampus\\Documents\\ServerFiles";
            String filePath = baseDir + "\\" + userEmail + ".jpg";

            if(userService.uploadUserImg(userEmail, filePath)) {
                files.transferTo(new File(filePath));
                return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
            } else {
                return ResponseEntity.status(666).body(BaseResponseBody.of(666, "No Email"));
            }
        } catch(Exception e) {
            return ResponseEntity.status(999).body(BaseResponseBody.of(999, "Fail"));
        }
    }


}