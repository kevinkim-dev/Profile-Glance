package com.profileglance.api.service;

import com.profileglance.api.request.RecruitPostReq;
import com.profileglance.api.response.RecruitPostRes;
import com.profileglance.db.entity.Recruit;
import com.profileglance.db.entity.Room;

import java.util.List;

public interface RecruitService {

    Boolean uploadRecruit(RecruitPostReq recruitPostReq);

    List<RecruitPostRes> searchByCompanyName(String companyName);

    List<RecruitPostRes> searchByJobName(String jobName);

    List<RecruitPostRes> searchAll();

    Boolean deleteRecruit(Long recruitId);

    Boolean updateRecruit(Room room, String companyId, String recruitId);

//    Boolean checkCSID(String csId);
}
