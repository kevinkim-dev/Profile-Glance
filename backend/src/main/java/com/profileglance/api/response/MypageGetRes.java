package com.profileglance.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Mypage_User")
public class MypageGetRes {

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

    @ApiModelProperty(name = "유저 닉네임")
    String userNickname;

    @ApiModelProperty(name = "역할")
    boolean isAdmin;

    @ApiModelProperty(name = "사진")
    String userImg;

    public MypageGetRes(String userName, String userEmail, String birth, String major1, String major2, Long countLike, Long countVideo, String portfolio1, String portfolio2) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.birth = birth;
        this.major1 = major1;
        this.major2 = major2;
        this.countLike = countLike;
        this.countVideo = countVideo;
        this.portfolio1 = portfolio1;
        this.portfolio2 = portfolio2;
    }

    public MypageGetRes(String userName, String userEmail, String birth, String major1, String major2, Long countLike, Long countVideo, String portfolio1, String portfolio2, String userNickname, boolean isAdmin, String userImg) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.birth = birth;
        this.major1 = major1;
        this.major2 = major2;
        this.countLike = countLike;
        this.countVideo = countVideo;
        this.portfolio1 = portfolio1;
        this.portfolio2 = portfolio2;
        this.userNickname = userNickname;
        this.isAdmin = isAdmin;
        this.userImg = userImg;
    }
}
