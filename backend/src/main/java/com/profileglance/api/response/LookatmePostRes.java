package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LookatmePostRes")
public class LookatmePostRes {

    @ApiModelProperty(name="룩앳미 아이디")
    Long lookatmeId;

    @ApiModelProperty(name="유저 닉네임")
    String userNickName;

    @ApiModelProperty(name="룩앳미 타이틀")
    String title;

    @ApiModelProperty(name="룩앳미 내용")
    String content;

    @ApiModelProperty(name="룩앳미 동영상")
    String video;

    @ApiModelProperty(name="룩앳미 썸네일")
    String thumbnail;

    @ApiModelProperty(name="룩앳미 카테코리")
    String category;

    @ApiModelProperty(name="룩앳미 조회수")
    Long view;

    @ApiModelProperty(name="룩앳미 좋아요")
    Long videoLike;

    @ApiModelProperty(name="룩앳미 등록시간")
    String createdAt;

    @ApiModelProperty(name="유저 이미지")
    String userImg;

    public LookatmePostRes(Long lookatmeId, String userNickName, String title, String content, String video, String thumbnail, String category, Long view, Long videoLike, String createdAt, String userImg) {
        this.lookatmeId = lookatmeId;
        this.userNickName = userNickName;
        this.title = title;
        this.content = content;
        this.video = video;
        this.thumbnail = thumbnail;
        this.category = category;
        this.view = view;
        this.videoLike = videoLike;
        this.createdAt = createdAt;
        this.userImg = userImg;
    }
}
