package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LeaveRoomInfoReq")
public class RoomInfoPostReq {

    @ApiModelProperty(name="세션 아이디")
    String sessionId;

    @ApiModelProperty(name="참여자 아이디")
    String viewer;


}
