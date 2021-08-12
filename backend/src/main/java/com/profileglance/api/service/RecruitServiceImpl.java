package com.profileglance.api.service;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.api.response.RecruitPostRes;
import com.profileglance.db.entity.*;
import com.profileglance.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitRepository recruitRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Boolean uploadRecruit(RecruitPostReq recruitPostReq) {

        Company company = companyRepository.findByCompanyId(recruitPostReq.getCompanyId()).get();

        String jobName = recruitPostReq.getJob();

        Job job = jobRepository.findByJobName(jobName).get();

        Recruit recruit = recruitRepository.save(Recruit.builder()
                .company(company)
                .job(job)
                .descriptionURL(recruitPostReq.getDescriptionURL())
                .recruitURL(recruitPostReq.getRecruitURL())
                .career(recruitPostReq.getCareer())
                .jobDetail(recruitPostReq.getJobDetail())
                .recruitStartDate(recruitPostReq.getRecruitStartDate())
                .recruitEndDate(recruitPostReq.getRecruitEndDate())
                .presentationDate(recruitPostReq.getPresentationDate())
                .csId(companyRepository.findByCompanyId(recruitPostReq.getCompanyId()).get().getSessionId())
                .build()
        );

        company.getRecruits().add(recruit);

        companyRepository.save(company);

        return true;
    }

    @Override
    public List<RecruitPostRes> searchByCompanyName(String companyName) {

        List<Recruit> recruitList = recruitRepository.findAllByCompany_CompanyNameContaining(companyName);
        List<RecruitPostRes> recruitPostResList = new ArrayList<>();

        for (Recruit recruit : recruitList) {

            String sessionId = null;
            if (recruit.getRoom() != null) {
                sessionId = recruit.getRoom().getSessionId();
            }

            recruitPostResList.add(new RecruitPostRes(
                    recruit.getRecruitId(),
                    recruit.getCompany().getCompanyName(),
                    recruit.getCompany().getCompanyImg(),
                    recruit.getJob().getJobName(),
                    recruit.getDescriptionURL(),
                    recruit.getRecruitURL(),
                    recruit.getCareer(),
                    recruit.getJobDetail(),
                    recruit.getRecruitStartDate(),
                    recruit.getRecruitEndDate(),
                    recruit.getPresentationDate(),
                    sessionId,
                    recruit.getCsId()
            ));
        }

        return recruitPostResList;

    }

    @Override
    public List<RecruitPostRes> searchByJobName(String jobName) {

        List<Recruit> recruitList = recruitRepository.findAllByJob_JobName(jobName);
        List<RecruitPostRes> recruitPostResList = new ArrayList<>();

        for (Recruit recruit : recruitList) {
            String sessionId = null;
            if (recruit.getRoom() != null) {
                sessionId = recruit.getRoom().getSessionId();
            }

            recruitPostResList.add(new RecruitPostRes(
                    recruit.getRecruitId(),
                    recruit.getCompany().getCompanyName(),
                    recruit.getCompany().getCompanyImg(),
                    recruit.getJob().getJobName(),
                    recruit.getDescriptionURL(),
                    recruit.getRecruitURL(),
                    recruit.getCareer(),
                    recruit.getJobDetail(),
                    recruit.getRecruitStartDate(),
                    recruit.getRecruitEndDate(),
                    recruit.getPresentationDate(),
                    sessionId,
                    recruit.getCsId()
            ));
        }

        return recruitPostResList;
    }

    @Override
    public List<RecruitPostRes> searchAll() {
        List<Recruit> recruitList = recruitRepository.findAllByOrderByRecruitEndDate();
        List<RecruitPostRes> recruitPostResList = new ArrayList<>();

        for (Recruit recruit : recruitList) {
            String sessionId = null;
            if (recruit.getRoom() != null) {
                sessionId = recruit.getRoom().getSessionId();
            }

            recruitPostResList.add(new RecruitPostRes(
                    recruit.getRecruitId(),
                    recruit.getCompany().getCompanyName(),
                    recruit.getCompany().getCompanyImg(),
                    recruit.getJob().getJobName(),
                    recruit.getDescriptionURL(),
                    recruit.getRecruitURL(),
                    recruit.getCareer(),
                    recruit.getJobDetail(),
                    recruit.getRecruitStartDate(),
                    recruit.getRecruitEndDate(),
                    recruit.getPresentationDate(),
                    sessionId,
                    recruit.getCsId()
            ));
        }

        return recruitPostResList;
    }

    @Override
    public Boolean deleteRecruit(Long recruitId) {

        recruitRepository.deleteByRecruitId(recruitId);

        return true;
    }

    @Override
    public Boolean updateRecruit(Room room, String companyId) {

        Recruit recruit = recruitRepository.findByCompany_CompanyId(companyId).get();

        recruit.setRoom(room);

        recruitRepository.save(recruit);

        return true;
    }

//    @Override
//    public Boolean checkCSID(String csId) {
//        List<Recruit> recruits = recruitRepository.findAllByCsId(csId);
//
//        for(Recruit recruit : recruits) {
//            if(recruit.getRoom() != null) {
//                return true;
//            }
//        }
//
//        return false;
//    }

}
