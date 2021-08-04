package com.profileglance.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("RecruitPostReq")
public class RecruitPostReq {

    @ApiModelProperty(name="직무")
    String job;

    @ApiModelProperty(name="회사 아이디")
    String companyId;

    @ApiModelProperty(name="기업 소개 URL")
    String descriptionURL;

    @ApiModelProperty(name="채용 공고 URL")
    String recruitURL;

    @ApiModelProperty(name="경력")
    String career;

    @ApiModelProperty(name="모집 분야")
    String jobDetail;

    @ApiModelProperty(name="채용 시작 기간")
    String recruitStartDate;

    @ApiModelProperty(name="채용 마감 기간")
    String recruitEndDate;

    @ApiModelProperty(name="설명회 일정")
    String presentationDate;

}
