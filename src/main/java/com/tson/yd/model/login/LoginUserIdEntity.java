package com.tson.yd.model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "userId返回实体类")
public class LoginUserIdEntity {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
