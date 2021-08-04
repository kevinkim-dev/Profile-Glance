package com.profileglance.api.controller;


import com.profileglance.api.request.UserLoginPostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.api.response.MypageGetRes;
import com.profileglance.api.service.UserService;
import com.profileglance.common.response.BaseResponseBody;
import com.profileglance.config.JwtTokenProvider;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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

        System.out.println(member.getUserEmail());
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

    // 회원탈퇴
    @DeleteMapping("/delete/{userEmail}")
    @ApiOperation(value = "회원 탈퇴", notes = "<strong>아이디</strong>를 통해 회원 탈퇴한다.")
    public ResponseEntity<? extends BaseResponseBody> delete(@PathVariable("userEmail") String userEmail) {

        userService.deleteUser(userEmail);

        return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
    }

    // 회원 이메일 중복 확인
    @GetMapping("/useremailcheck/{userEmail}")
    @ApiOperation(value = "회원 이메일 중복 확인", notes = "DB에 있으면 202, 없으면 201")
    public ResponseEntity<? extends BaseResponseBody> userEmailCheck(@PathVariable("userEmail") String userEmail){
        if (userRepository.findByUserEmail(userEmail).isPresent()){
            return ResponseEntity.status(202).body(BaseResponseBody.of(202, "이메일이 중복입니다"));
        }else {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "이메일 사용가능"));
        }
    }
    // 회원 닉네임 중복 확인
    @GetMapping("/usernicknamecheck/{userNickname}")
    @ApiOperation(value = "회원 닉네임 중복 확인", notes = "DB에 있으면 202, 없으면 201")
    public ResponseEntity<? extends BaseResponseBody> userNicknameCheck(@PathVariable("userNickname") String userNickname){
        if (userRepository.findByUserNickname(userNickname).isPresent()){
            return ResponseEntity.status(202).body(BaseResponseBody.of(202, "닉네임이 중복입니다"));
        }else {
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "닉네임 사용가능"));
        }
    }

    // 정보수정
    @PutMapping("/update")
    @ApiOperation(value = "정보수정", notes = "<strong>userEmail, major1, major2, Portfolio1, Portfolio2</strong>만 넣으면 됩니다.")
    public ResponseEntity<User> update(@RequestBody User userUpdateInfo) {

        User user = userService.updateUser(userUpdateInfo, userUpdateInfo.getUserEmail());

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // 내 정보 보기
    @GetMapping("/myinfo/{userEmail}")
    @ApiOperation(value = "내 정보 가져오기", notes = "회원 테이블에 있는것들 전부 준다.")
    public ResponseEntity<MypageGetRes> myinfo(@PathVariable("userEmail") String userEmail){

        MypageGetRes mypageGetRes = userService.myInfo(userEmail);

        return new ResponseEntity<MypageGetRes>(mypageGetRes, HttpStatus.OK);
    }

    // 내 영상 목록 가져오기
    @GetMapping("/myvideo/{userEmail}")
    @ApiOperation(value = "내 영상 리스트 가져오기")
    public ResponseEntity<List<LookatmePostRes>> myVideoList(@PathVariable("userEmail") String userEmail){

        return new ResponseEntity<List<LookatmePostRes>>(userService.myVideoList(userEmail), HttpStatus.OK);
    }

    // 닉네임으로 내 정보 보기
    @GetMapping("/myinfo/nickname/{userNickname}")
    @ApiOperation(value = "닉네임으로 내 정보 가져오기", notes = "회원 테이블에 있는것들 전부 준다.")
    public ResponseEntity<MypageGetRes> myInfoByUserNickname(@PathVariable("userNickname") String userNickname){
        MypageGetRes mypageGetRes = userService.myInfoByNickname(userNickname);
        return new ResponseEntity<MypageGetRes>(mypageGetRes, HttpStatus.OK);
    }

    // (마이페이지) 면접 일정 보기
    @GetMapping("/myinterview/{userEmail}")
    @ApiOperation(value = "나의 면접 일정 보기", notes = "userEmail을 주세용")
    public ResponseEntity<List<Interview>> myInterview(@PathVariable("userEmail") String userEmail){
        return new ResponseEntity<List<Interview>>(userService.myInterviewList(userEmail), HttpStatus.OK);
    }
}