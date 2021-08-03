package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("RecruitPostRes")
public class RecruitPostRes {

    @ApiModelProperty(name = "채용 아이디")
    Long recruitId;
    
    @ApiModelProperty(name = "회사 이름")
    String companyName;

    @ApiModelProperty(name="직무")
    String job;

    @ApiModelProperty(name="기업 소개 URL")
    String descriptionURL;

    @ApiModelProperty(name="채용 공고 URL")
    String recruitURL;

    @ApiModelProperty(name="경력")
    String career;

    @ApiModelProperty(name="모집 분야")
    String jobDetail;

    @ApiModelProperty(name="채용 기간")
    String recruitDate;

    @ApiModelProperty(name="설명회 일정")
    String presentationDate;

    public RecruitPostRes(Long recruitId, String companyName, String job, String descriptionURL, String recruitURL, String career, String jobDetail, String recruitDate, String presentationDate) {
        this.recruitId = recruitId;
        this.companyName = companyName;
        this.job = job;
        this.descriptionURL = descriptionURL;
        this.recruitURL = recruitURL;
        this.career = career;
        this.jobDetail = jobDetail;
        this.recruitDate = recruitDate;
        this.presentationDate = presentationDate;
    }
}
