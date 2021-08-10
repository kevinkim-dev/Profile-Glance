package com.profileglance.db.repository;

import com.profileglance.db.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

    List<Interview> findAllByUser_UserNickname(String userNickname);
    List<Interview> findAllByCompany_CompanyId(String companyId);
    Optional<Interview> findByUser_UserNicknameAndCompany_CompanyId(String userNickname, String companyId);

    @Transactional
    void deleteByRoom_SessionId(String sessionId);

}
