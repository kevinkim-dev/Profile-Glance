package com.profileglance.api.service;

import com.profileglance.api.request.InterviewPostReq;
import com.profileglance.api.response.CompanyInterviewGetRes;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Room;
import com.profileglance.db.repository.CompanyRepository;
import com.profileglance.db.repository.InterviewRepository;
import com.profileglance.db.repository.RoomRepository;
import com.profileglance.db.repository.UserRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService{

    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public Boolean createInterview(InterviewPostReq interviewPostReq) {

        try {
            interviewRepository.save(Interview.builder()
                    .user(userRepository.findByUserNickname(interviewPostReq.getUserNickname()).get())
                    .company(companyRepository.findByCompanyId(interviewPostReq.getCompanyId()).get())
                    .interviewTime(interviewPostReq.getInterviewTime())
                    .interviewDate(interviewPostReq.getInterviewDate())
                    .csId(companyRepository.findByCompanyId(interviewPostReq.getCompanyId()).get().getSessionId())
                    .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Interview updateSesstion(Room room, String userNickname, String companyId) {
        Interview updateinterview = interviewRepository.findByUser_UserNicknameAndCompany_CompanyId(userNickname, companyId).get();
        updateinterview.setRoom(room);
        interviewRepository.save(updateinterview);
        return updateinterview;
    }

    @Override
    public Boolean checkCSID(String userNickName, String csId) {
        Optional<Interview> interview = interviewRepository.findByUser_UserNicknameAndCsId(userNickName, csId);

        if(interview.isPresent() && interview.get().getRoom() != null) {
            return true;
        } else {
            return  false;
        }
    }

}
