package com.tson.yd.model.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "验证码返回实体")
public class EmailEntity extends InsertEmailRequest {

    @ApiModelProperty(value = "创建时间")
    private long createTime;
    @ApiModelProperty(value = "更新时间")
    private long updateTime;

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
}
