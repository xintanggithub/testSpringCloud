package com.tson.yd.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "更新用户信息实体类")
public class UpdateUserRequest extends InsertUserRequest {

    @ApiModelProperty(value = "用户ID 用于查询校验，非更新项")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
