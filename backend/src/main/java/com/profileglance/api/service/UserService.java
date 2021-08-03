package com.profileglance.api.service;

import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User userRegisterInfo);
    boolean uploadUserImg(String userEmail, String userImg);
    User updateUser(User userUpdateInfo, String userEmail);
    boolean deleteUser(String userEmail);

    User myinfo(String userEmail);
    List<LookatmePostRes> myVideoList(String userEmail);
    List<Interview> myInterviewList(String userEmail);
}