package com.profileglance.api.service;

import com.profileglance.api.request.InterviewPostReq;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Room;

public interface InterviewService {

    Boolean createInterview(InterviewPostReq interviewPostReq);

    Interview updateSesstion(Room room, String userNickname, String companyId);

    Boolean checkCSID(String userNickName, String csId);

}
