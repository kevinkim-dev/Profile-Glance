package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CompanyMypageGetRes")
public class CompanyMypageGetRes {

    @ApiModelProperty(name="기업아이디")
    String companyId;

    @ApiModelProperty(name="기업 이름")
    String companyName;

    @ApiModelProperty(name="기업 이메일")
    String companyEmail;

    @ApiModelProperty(name="기업 연락처")
    String companyPhone;

    @ApiModelProperty(name="기업 로고 사진")
    String companyImg;

    //csid
    @ApiModelProperty(name="기업 세션 명")
    String csId;

    public CompanyMypageGetRes(String companyId, String companyName, String companyEmail, String companyPhone, String companyImg, String csId) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyImg = companyImg;
        this.csId = csId;
    }
}
