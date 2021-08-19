package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RoomInterviewDeleteReq")
public class RoomInterviewDeleteReq {

    @ApiModelProperty(name="세션 아이디")
    String sessionId;

    @ApiModelProperty(name="회사 아이디")
    String companyId;
    
}
