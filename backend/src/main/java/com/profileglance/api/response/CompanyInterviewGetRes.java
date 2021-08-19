package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CompanyInterviewGetRes")
public class CompanyInterviewGetRes {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="인터뷰 날짜")
    String interviewDate;

    @ApiModelProperty(name="인터뷰 시간")
    String interviewTime;

    @ApiModelProperty(name = "세션명")
    String sessionId;

    @ApiModelProperty(name = "company_session_id")
    String csId;

    public CompanyInterviewGetRes(String userNickname, String interviewDate, String interviewTime, String sessionId, String csId) {
        this.userNickname = userNickname;
        this.interviewDate = interviewDate;
        this.interviewTime = interviewTime;
        this.sessionId = sessionId;
        this.csId = csId;
    }
}
