package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("InterviewPostReq")
@Data
public class InterviewPostReq {

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="회사 아이디")
    String companyId;

    @ApiModelProperty(name = "면접 날짜")
    String interviewDate;

    @ApiModelProperty(name = "면접 시간")
    String interviewTime;
}
