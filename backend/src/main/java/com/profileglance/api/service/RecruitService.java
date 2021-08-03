package com.profileglance.api.service;

import com.profileglance.api.request.RecruitPostReq;

public interface RecruitService {

    Boolean uploadRecruit(RecruitPostReq recruitPostReq, String companyId);

}
