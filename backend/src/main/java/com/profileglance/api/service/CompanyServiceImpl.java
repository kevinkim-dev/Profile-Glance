package com.profileglance.api.service;

import com.profileglance.api.request.CompanyPostReq;
import com.profileglance.api.response.CompanyLikeListGetRes;
import com.profileglance.api.response.CompanyMypageGetRes;
import com.profileglance.db.entity.Company;
import com.profileglance.db.entity.User;
import com.profileglance.db.entity.UserLike;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.UserLikeRepository;
import com.profileglance.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
    public Boolean createCompany(CompanyPostReq companyPostReq) {

//        String baseDir = "C:\\profile_glance\\ServerFiles";
//        String baseDir = "C:\\Users\\multicampus\\Documents\\S05P13A402\\frontend\\public\\ServerFiles";
//        String filePath = baseDir + "\\CompanyLogo\\" + companyPostReq.getCompanyId() + ".jpg";

        //서버용
        String baseDir = "/home/ubuntu/profile_glance/dist/ServerFiles";
        String filePath = baseDir + "/CompanyLogo/" + companyPostReq.getCompanyId() + ".jpg";

        try{
            companyPostReq.getCompanyImg().transferTo(new File(filePath));
        } catch (Exception e){
            return false;
        }

        companyRepository.save(Company.builder()
                .companyId(companyPostReq.getCompanyId())
                .companyName(companyPostReq.getCompanyName())
                .companyEmail(companyPostReq.getCompanyEmail())
                .companyPassword(passwordEncoder.encode(companyPostReq.getCompanyPassword()))
                .companyPhone(companyPostReq.getCompanyPhone())
                .companyImg(companyPostReq.getCompanyId() + ".jpg")
                .build()
        );

        return true;
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
    @Transactional
    public void deleteLikeByCompany(String userEmail, String companyId){
        userLikeRepository.deleteByUser_UserEmailAndCompany_CompanyId(userEmail, companyId);
    }

     // 기업 A가 좋아요를 누른 유저의 목록
    @Override
    public List<CompanyLikeListGetRes> userLikeListByCompany(String companyId){
        List<CompanyLikeListGetRes> companyLikeListGetResList = new ArrayList<>();

        List<UserLike> userLikes = userLikeRepository.findAllByCompany_CompanyId(companyId);

        for (UserLike u : userLikes){
            companyLikeListGetResList.add(new CompanyLikeListGetRes(
                    u.getUser().getUserName()
                    ,u.getUser().getUserEmail()
                    ,u.getUser().getUserNickname()
                    ,u.getCompany().getCompanyId()
            ));
        }
        return companyLikeListGetResList;
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

    // 기업회원 아이디로 정보 조회
    @Override
    public CompanyMypageGetRes companyInfo(String companyId){
        Company company = companyRepository.findByCompanyId(companyId).get();
        CompanyMypageGetRes companyMypageGetRes = new CompanyMypageGetRes(
                company.getCompanyId()
                ,company.getCompanyName()
                ,company.getCompanyEmail()
                ,company.getCompanyPhone()
                ,company.getCompanyImg()
        );
        return companyMypageGetRes;
    }
}
