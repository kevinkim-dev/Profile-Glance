package com.profileglance.api.service;

import com.profileglance.db.entity.User;

public interface UserService {
    User createUser(User userRegisterInfo);
    boolean uploadUserImg(String userEmail, String userImg);
    User updateUser(User userUpdateInfo, String userEmail);
    boolean deleteUser(String userEmail);
}