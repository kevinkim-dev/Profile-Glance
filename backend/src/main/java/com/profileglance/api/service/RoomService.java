package com.profileglance.api.service;

import com.profileglance.api.request.RoomDeleteReq;

public interface RoomService {

    Boolean deleteRoom(RoomDeleteReq roomReq);

    Boolean deleteInterview(RoomDeleteReq roomReq);

    Boolean deleteRecruitSessionId(RoomDeleteReq roomReq);

    String findRoomCategory(String sessionId);

}
