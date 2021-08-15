package com.profileglance.db.repository;


import com.profileglance.db.entity.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {

    @Transactional
    void deleteByViewerAndRoom_SessionId(String viewer, String sessionId);

    Long countByRoom_SessionId(String sessionId);
}
