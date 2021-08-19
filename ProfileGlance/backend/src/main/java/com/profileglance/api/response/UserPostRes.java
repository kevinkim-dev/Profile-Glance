package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPostRes")
public class UserPostRes {

    @ApiModelProperty(name="이름")
    String userName;

    @ApiModelProperty(name="이메일")
    String userEmail;

    @ApiModelProperty(name="생년월일")
    String birth;

    @ApiModelProperty(name="전공1")
    String major1;

    @ApiModelProperty(name="전공2")
    String major2;

    @ApiModelProperty(name="좋아요 갯수")
    Long countLike;

    @ApiModelProperty(name="내 영상 갯수")
    Long countVideo;

    @ApiModelProperty(name="포트폴리오1")
    String portfolio1;

    @ApiModelProperty(name="포트폴리오2")
    String portfolio2;

}
