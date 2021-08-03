package com.profileglance.api.service;

import com.profileglance.db.entity.Company;
import com.profileglance.db.entity.User;
import com.profileglance.db.entity.UserLike;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.UserLikeRepository;
import com.profileglance.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserLikeRepository userLikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Company createCompany(Company company) {

        return companyRepository.save(Company.builder()
                .companyId(company.getCompanyId())
                .companyName(company.getCompanyName())
                .companyEmail(company.getCompanyEmail())
                .companyPassword(passwordEncoder.encode(company.getCompanyPassword()))
                .companyPhone(company.getCompanyPhone())
//                .companyImg(company.getCompanyImg())
                .build()
        );
    }

    // 기업이 회원에 좋아요를 누른다.
    @Override
    public void addLikeByCompany(String userEmail, String companyId){
        userLikeRepository.save(UserLike.builder()
                .user(userRepository.findByUserEmail(userEmail).get())
                .company(companyRepository.findByCompanyId(companyId).get())
                .build());
    }

    // 기업이 회원에 좋아요를 취소한다.
    @Override
    public void deleteLikeByCompany(String userEmail, String companyId){
        userLikeRepository.deleteByUser_UserEmailAndCompany_CompanyId(userEmail, companyId);
    }

    // 기업 A가 좋아요를 누른 유저의 목록
    @Override
    public List<User> userLikeListByCompany(String companyId){
        List<User> user = new ArrayList<>();

        List<UserLike> userLikes = userLikeRepository.findAllByCompany_CompanyId(companyId);

        for (UserLike u : userLikes){
            user.add(userRepository.findByUserEmail(u.getUser().getUserEmail()).get());
        }
        return user;
    }

    // 좋아요를 눌렀는지 확인
    @Override
    public boolean isHitLike(String userEmail,String companyId){
        if (userLikeRepository.findByUser_UserEmailAndCompany_CompanyId(userEmail,companyId).isPresent()){
            return true;
        }else{
            return false;
        }
    }


}
