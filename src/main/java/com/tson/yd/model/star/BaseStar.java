package com.tson.yd.model.star;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "评星")
public class BaseStar {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "评星用户ID")
    private String starUserId;

    @ApiModelProperty(value = "评星用户名")
    private String starUserName;

    @ApiModelProperty(value = "评星状态：1 是 0否")
    private int starStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStarUserId() {
        return starUserId;
    }

    public void setStarUserId(String starUserId) {
        this.starUserId = starUserId;
    }

    public String getStarUserName() {
        return starUserName;
    }

    public void setStarUserName(String starUserName) {
        this.starUserName = starUserName;
    }

    public int getStarStatus() {
        return starStatus;
    }

    public void setStarStatus(int starStatus) {
        this.starStatus = starStatus;
    }

}
