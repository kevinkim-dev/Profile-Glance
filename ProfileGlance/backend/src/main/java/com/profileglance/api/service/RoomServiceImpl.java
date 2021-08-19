package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.api.request.RoomInterviewDeleteReq;
import com.profileglance.db.entity.Company;
import com.profileglance.db.entity.Recruit;
import com.profileglance.db.entity.Room;
import com.profileglance.db.entity.RoomInfo;
import com.profileglance.db.repository.*;
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
    @Autowired
    RoomInfoRepository roomInfoRepository;

    @Override
    public Boolean deleteRoom(RoomDeleteReq roomDeleteReq) {

        roomRepository.deleteBySessionIdAndHost(roomDeleteReq.getSessionId(), roomDeleteReq.getCompanyId());

        return true;
    }

    @Override
    public Boolean deleteRoom(RoomInterviewDeleteReq roomDeleteReq) {

        roomRepository.deleteBySessionIdAndHost(roomDeleteReq.getSessionId(), roomDeleteReq.getCompanyId());

        return true;
    }

    @Override
    public Boolean deleteInterview(RoomInterviewDeleteReq roomDeleteReq) {

        interviewRepository.deleteByRoom_SessionId(roomDeleteReq.getSessionId());

        return true;
    }

    @Override
    public Boolean deleteRecruitSessionId(RoomDeleteReq roomDeleteReq) {

        Recruit recruit = recruitRepository.findByCompany_CompanyIdAndRecruitId(roomDeleteReq.getCompanyId(),roomDeleteReq.getRecruitId()).get();

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
    public Room createRoom(String companyId, String room_category, String createAt) {
        Company company = companyRepository.findByCompanyId(companyId).get();

        Optional<Room> room = roomRepository.findBySessionId(company.getSessionId());
        if (room.isPresent()) {
            return room.get();
        } else {
            Room newroom = roomRepository.save(Room.builder()
                    .sessionId(company.getSessionId())
                    .host(companyId)
                    .roomCategory(room_category)
                    .createAt(createAt)
                    .build());
            return newroom;
        }
    }

    @Override
    public Boolean leaveSession(String viewer, String sessionId) {
        try {
            roomInfoRepository.deleteByViewerAndRoom_SessionId(viewer, sessionId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean joinSession(String viewer, String sessionId){
        try {
            roomInfoRepository.save(RoomInfo.builder()
                    .room(roomRepository.findBySessionId(sessionId).get())
                    .viewer(viewer)
                    .build());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Long countViewer(String sessionId) {
        return roomInfoRepository.countByRoom_SessionId(sessionId);
    }

    @Override
    public String findRoomTime(String sessionId) {
        return roomRepository.findBySessionId(sessionId).get().getCreateAt();
    }
}