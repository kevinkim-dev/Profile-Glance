package com.profileglance.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LookatmePostReq")
public class LookatmePostReq {

    @ApiModelProperty(name="룩앳미 아이디")
    Long lookatmeId;

    @ApiModelProperty(name="룩앳미 타이틀")
    String title;

    @ApiModelProperty(name="룩앳미 내용")
    String content;

    @ApiModelProperty(name="룩앳미 동영상")
    String video;

    @ApiModelProperty(name="룩앳미 썸네일")
    String thumbnail;

    @ApiModelProperty(name="룩앳미 카테고리")
    String category;

}
