package com.tson.yd.model.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "评星实体")
public class HistoryResponse extends BaseHistory {

    @ApiModelProperty(value = "自增长ID")
    private int id;
    @ApiModelProperty(value = "创建时间")
    private long createTime;
    @ApiModelProperty(value = "更新时间")
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
