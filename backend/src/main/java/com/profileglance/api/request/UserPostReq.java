package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPostReq")
public class UserPostReq {

    @ApiModelProperty(name="유저 이름")
    String userName;

    @ApiModelProperty(name="유저 이메일")
    String userEmail;

    @ApiModelProperty(name="유저 닉네임")
    String userNickname;

    @ApiModelProperty(name="유저 비밀번호")
    String userPassword;

    @ApiModelProperty(name="유저 전공1")
    String major1;

    @ApiModelProperty(name="유저 전공2")
    String major2;

    @ApiModelProperty(name="유저 핸드폰 번호")
    String userPhone;

    @ApiModelProperty(name="유저 생년월일")
    String birth;
}
