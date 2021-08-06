package com.profileglance.api.service;

import com.profileglance.api.request.MypagePostReq;
import com.profileglance.api.request.UserPostReq;
import com.profileglance.api.response.InterviewListGetRes;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.api.response.MypageGetRes;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User createUser(UserPostReq userPostReq);
    boolean uploadUserImg(MultipartFile files, String userEmail);
    MypageGetRes updateUser(MypagePostReq mypagePostReq);
    boolean deleteUser(String userEmail);

    List<LookatmePostRes> myVideoList(String userEmail);
    List<InterviewListGetRes> myInterviewList(String userEmail);
    Long likeCount(String userEmail);
    MypageGetRes myInfo(String userEmail);
    MypageGetRes myInfoByNickname(String userNickname);
}