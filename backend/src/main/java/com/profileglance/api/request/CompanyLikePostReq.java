package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("CompanyLikeGetReq")
@Data
public class CompanyLikePostReq {
    @ApiModelProperty(name="회사 아이디")
    String companyId;

    @ApiModelProperty(name="유저 이메일")
    String userNickname;
}
