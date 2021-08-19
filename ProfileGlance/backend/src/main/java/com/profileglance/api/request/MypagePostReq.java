package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("MypagePostReq")
public class MypagePostReq {

    @ApiModelProperty(name="유저 이메일")
    String userEmail;

    @ApiModelProperty(name="유저 전공1")
    String major1;

    @ApiModelProperty(name="유저 전공2")
    String major2;

    @ApiModelProperty(name="유저 핸드폰 번호")
    String userPhone;

    @ApiModelProperty(name="유저 portfolio1")
    String portfolio1;

    @ApiModelProperty(name="유저 portfolio2")
    String portfolio2;
}
