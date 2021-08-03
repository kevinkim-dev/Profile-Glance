package com.profileglance.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LookatmePostReq")
public class LookatmePostReq {

    @ApiModelProperty(name="룩앳미 등록 타이틀")
    String title;

    String content;

    String video;

    String thumbnail;

    String category;

}
