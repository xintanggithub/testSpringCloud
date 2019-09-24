package com.tson.yd.model;

import com.tson.yd.model.request.UpdateUserRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户信息实体类")
public class UserEntity extends UpdateUserRequest {

    @ApiModelProperty(value = "创建时间")
    private long createTime;

    @ApiModelProperty(value = "更新时间")
    private long updateTime;

    @ApiModelProperty(value = "用户状态 1正常 0删除")
    private int userStatus;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
