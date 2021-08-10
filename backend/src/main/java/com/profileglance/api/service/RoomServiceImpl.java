package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.db.entity.Recruit;
import com.profileglance.db.entity.Room;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.InterviewRepository;
import com.profileglance.db.repository.RecruitRepository;
import com.profileglance.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    RecruitRepository recruitRepository;

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

        Recruit recruit = recruitRepository.findByCompany_CompanyId(roomReq.getCompanyId()).get();

        recruit.setRoom(null);

        recruitRepository.save(recruit);

        return true;
    }

    @Override
    public String findRoomCategory(String sessionId) {

        Optional<Room> room = roomRepository.findBySessionId(sessionId);
        if(room.isPresent()) {
            return room.get().getRoomCategory();
        } else {
            return null;
        }

    }
}
