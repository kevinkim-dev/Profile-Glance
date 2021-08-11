package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Interview_User")
public class InterviewListGetRes {

    @ApiModelProperty(name = "유저 이름")
    String userName;

    @ApiModelProperty(name = "기업 아이디")
    String companyId;

    @ApiModelProperty(name = "면접 날짜")
    String interviewDate;

    @ApiModelProperty(name = "면접 시간")
    String interviewTime;

    @ApiModelProperty(name = "면접방 주소")
    String sessionId;

    public InterviewListGetRes(String userName, String companyId, String interviewDate, String interviewTime, String sessionId) {
        this.userName = userName;
        this.companyId = companyId;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.sessionId = sessionId;
    }
}
