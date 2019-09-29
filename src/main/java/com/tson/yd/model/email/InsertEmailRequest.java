package com.tson.yd.model.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "验证码插入实体")
public class InsertEmailRequest {
    @ApiModelProperty(value = "账号")
    private String userCode;
    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
