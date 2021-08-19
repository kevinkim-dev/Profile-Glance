package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InterviewCheckPostReq")
@Data
public class InterviewCheckPostReq {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="회사 세션 아이디")
    String csId;

}
