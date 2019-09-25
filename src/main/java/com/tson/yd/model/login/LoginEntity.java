package com.tson.yd.model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "登录返回实体类")
public class LoginEntity extends LoginRegisterEntity {

    @ApiModelProperty(value = "账号")
    private String userCode;
    @ApiModelProperty(value = "密码")
    private String password;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
