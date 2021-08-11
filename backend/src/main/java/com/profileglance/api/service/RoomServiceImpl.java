package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.db.entity.Company;
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
    public Boolean deleteRoom(RoomDeleteReq roomDeleteReq) {

        roomRepository.deleteBySessionIdAndHost(roomDeleteReq.getSessionId(), roomDeleteReq.getCompanyId());

        return true;
    }

    @Override
    public Boolean deleteInterview(RoomDeleteReq roomDeleteReq) {

        interviewRepository.deleteByRoom_SessionId(roomDeleteReq.getSessionId());

        return true;
    }

    @Override
    public Boolean deleteRecruitSessionId(RoomDeleteReq roomDeleteReq) {

        Recruit recruit = recruitRepository.findByCompany_CompanyId(roomDeleteReq.getCompanyId()).get();

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

    @Override
    public Room createRoom(String companyId, String room_category) {
        Company company = companyRepository.findByCompanyId(companyId).get();

        Optional<Room> room = roomRepository.findBySessionId(company.getSessionId());
        if (room.isPresent()) {
            return room.get();
        } else {
            Room newroom = roomRepository.save(Room.builder()
                    .sessionId(company.getSessionId())
                    .host(companyId)
                    .roomCategory(room_category)
                    .build());
            return newroom;
        }
    }
}