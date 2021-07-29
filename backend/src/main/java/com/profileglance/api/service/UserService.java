package com.profileglance.api.service;

import com.profileglance.db.entity.User;

import java.util.Optional;

public interface UserService {
    User createUser(User userRegisterInfo);
    User updateUser(User userUpdateInfo, String userEmail);
    boolean deleteUser(String userEmail);
}