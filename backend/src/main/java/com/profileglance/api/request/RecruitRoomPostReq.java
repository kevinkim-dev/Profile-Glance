package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InterviewPostReq")
@Data
public class RecruitRoomPostReq {

    @ApiModelProperty(name="채용 아이디")
    Long recruitId;

    @ApiModelProperty(name="회사 아이디")
    String companyId;

}
