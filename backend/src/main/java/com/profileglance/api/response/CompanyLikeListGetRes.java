package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CompanyLikeListGetRes")
public class CompanyLikeListGetRes {

    @ApiModelProperty(name="유저 이름")
    String userName;

    @ApiModelProperty(name="유저 이메일")
    String userEmail;

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="유저 사진")
    String userImg;

    @ApiModelProperty(name="기업 아이디")
    String companyId;

    public CompanyLikeListGetRes(String userName, String userEmail, String userNickname, String userImg, String companyId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userImg = userImg;
        this.companyId = companyId;
    }

    public CompanyLikeListGetRes(String userNickname) {
        this.userNickname = userNickname;
    }

    public CompanyLikeListGetRes(String userNickname, String userImg) {
        this.userNickname = userNickname;
        this.userImg = userImg;
    }
}
