package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ApiModel("CompanyPostReq")
@Data
public class CompanyPostReq {

    @ApiModelProperty(name="회사 아이디")
    String companyId;

    @ApiModelProperty(name="회사 이름")
    String companyName;

    @ApiModelProperty(name="회사 이메일")
    String companyEmail;

    @ApiModelProperty(name="회사 비밀번호")
    String companyPassword;

    @ApiModelProperty(name="회사 번호")
    String companyPhone;

    @ApiModelProperty(name="회사 로고")
    MultipartFile companyImg;

    @ApiModelProperty(name = "회사 부서")
    String companyDept;

    @ApiModelProperty(name = "회사 이름 영문")
    String companyNameEng;

    @ApiModelProperty(name = "회사 부서 영문")
    String companyDeptEng;
}
