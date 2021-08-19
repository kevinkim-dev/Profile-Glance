package com.profileglance.api.service;

import com.profileglance.api.request.CompanyPostReq;
import com.profileglance.api.response.CompanyInterviewGetRes;
import com.profileglance.api.response.CompanyLikeListGetRes;
import com.profileglance.api.response.CompanyMypageGetRes;
import com.profileglance.api.response.RecruitPostRes;
import com.profileglance.config.DirPathConfig;
import com.profileglance.db.entity.*;
import com.profileglance.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
    InterviewRepository interviewRepository;
    @Autowired
    RecruitRepository recruitRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    static DirPathConfig dirPathConfig = new DirPathConfig();
    static String baseDir = dirPathConfig.baseDir;

    @Override
    public Boolean createCompany(CompanyPostReq companyPostReq) {

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
                .companyDept(companyPostReq.getCompanyDept())
                .sessionId(companyPostReq.getCompanyNameEng()+companyPostReq.getCompanyDeptEng())
                .companyNameEng(companyPostReq.getCompanyNameEng())
                .companyDeptEng(companyPostReq.getCompanyDeptEng())
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
                    u.getUser().getUserNickname(),
                    u.getUser().getUserImg()
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
                ,company.getSessionId()
        );
        return companyMypageGetRes;
    }

    @Override
    public List<CompanyInterviewGetRes> interviewList(String csId){
        List<CompanyInterviewGetRes> companyInterviewGetResList = new ArrayList<>();
        List<Interview> interviewList = interviewRepository.findAllByCompany_SessionId(csId);

        for (Interview i : interviewList){
            String sessionId = null;
            if (i.getRoom() != null){
                sessionId = i.getRoom().getSessionId();
            }
            companyInterviewGetResList.add(new CompanyInterviewGetRes(
                    i.getUser().getUserNickname()
                    ,i.getInterviewDate()
                    ,i.getInterviewTime()
                    ,sessionId
                    ,i.getCsId()
            ));
        }

        return companyInterviewGetResList;
    }

    @Override
    public List<RecruitPostRes> recruitList(String csId) {
        List<RecruitPostRes> recruitPostResList = new ArrayList<>();
        List<Recruit> recruits = recruitRepository.findAllByCsId(csId);

        for(Recruit recruit : recruits) {
            String sessionId = null;
            if(recruit.getRoom() != null) {
                sessionId = recruit.getRoom().getSessionId();
            }

            recruitPostResList.add(new RecruitPostRes(
                    recruit.getRecruitId()
                    ,recruit.getCompany().getCompanyName()
                    ,recruit.getCompany().getCompanyImg()
                    ,recruit.getJob().getJobName()
                    ,recruit.getDescriptionURL()
                    ,recruit.getRecruitURL()
                    ,recruit.getCareer()
                    ,recruit.getJobDetail()
                    ,recruit.getRecruitStartDate()
                    ,recruit.getRecruitEndDate()
                    ,recruit.getPresentationDate()
                    ,sessionId
                    ,recruit.getCsId()
            ));
        }

        return recruitPostResList;
    }
}
