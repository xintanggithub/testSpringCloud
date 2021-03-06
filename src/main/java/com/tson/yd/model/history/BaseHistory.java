package com.tson.yd.model.history;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "历史")
public class BaseHistory {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "对应业务ID")
    private String businessId;

    @ApiModelProperty(value = "对应业务名")
    private String businessName;

    @ApiModelProperty(value = "删除状态：1 已删除  0未删除")
    private int delStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(int delStatus) {
        this.delStatus = delStatus;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
