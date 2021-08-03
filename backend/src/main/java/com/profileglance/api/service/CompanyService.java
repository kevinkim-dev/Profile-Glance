package com.profileglance.api.service;

import com.profileglance.db.entity.Company;
import com.profileglance.db.entity.User;

import java.util.List;

public interface CompanyService {

    Company createCompany(Company company);

    List<User> userLikeListByCompany(String companyId);
    boolean isHitLike(String userEmail,String companyId);
    void addLikeByCompany(String userEmail, String companyId);
    void deleteLikeByCompany(String userEmail, String companyId);
}
