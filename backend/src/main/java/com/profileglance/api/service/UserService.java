package com.profileglance.api.service;

import com.profileglance.db.entity.User;

import java.util.Optional;

public interface UserService {
    User createUser(User userRegisterInfo);
    Optional<User> updateUser(User userUpdateInfo, String userId);
    boolean deleteUser(String userEmail);
}