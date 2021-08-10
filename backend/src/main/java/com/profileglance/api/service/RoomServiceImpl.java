package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.InterviewRepository;
import com.profileglance.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @Override
    public Boolean deleteRoom(RoomDeleteReq roomReq) {

        roomRepository.deleteBySessionIdAndHost(roomReq.getSessionId(), roomReq.getCompanyId());

        return true;
    }

    @Override
    public Boolean deleteInterview(RoomDeleteReq roomReq) {

        interviewRepository.deleteByRoom_SessionId(roomReq.getSessionId());

        return true;
    }

    @Override
    public Boolean deleteRecruitSessionId(RoomDeleteReq roomReq) {
        return true;
    }

    @Override
    public String findRoomCategory(String sessionId) {
        return null;
    }
}
