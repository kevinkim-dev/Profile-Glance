package com.profileglance.api.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("LookatmePostReq")
@Data
public class LookatmePostReq {

    @ApiModelProperty(name="룩앳미 아이디")
    Long lookatmeId;

    @ApiModelProperty(name="룩앳미 타이틀")
    String title;

    @ApiModelProperty(name="룩앳미 내용")
    String content;

    @ApiModelProperty(name="룩앳미 동영상")
    MultipartFile video;

    @ApiModelProperty(name="룩앳미 썸네일")
    MultipartFile thumbnail;

    @ApiModelProperty(name="룩앳미 카테고리")
    String category;

    @ApiModelProperty(name="유저 이메일")
    String userEmail;

}
