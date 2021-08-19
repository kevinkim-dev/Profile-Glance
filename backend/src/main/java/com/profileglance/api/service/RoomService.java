package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;
import com.profileglance.api.request.RoomInterviewDeleteReq;
import com.profileglance.db.entity.Room;

public interface RoomService {

    Boolean deleteRoom(RoomDeleteReq roomDeleteReq);

    Boolean deleteRoom(RoomInterviewDeleteReq roomDeleteReq);

    Boolean deleteInterview(RoomInterviewDeleteReq roomDeleteReq);

    Boolean deleteRecruitSessionId(RoomDeleteReq roomDeleteReq);

    String findRoomCategory(String sessionId);

    Room createRoom(String companyId, String room_category, String createAt);

    Boolean leaveSession(String viewer, String sessionId);

    Boolean joinSession(String viewer, String sessionId);

    Long countViewer(String sessionId);

    String findRoomTime(String sessionId);
}
