package com.profileglance.db.repository;

import com.profileglance.db.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findBySessionId(String sessionId);

    @Transactional
    void deleteBySessionIdAndHost(String sessionId, String host);

}
