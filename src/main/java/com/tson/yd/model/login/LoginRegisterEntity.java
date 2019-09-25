package com.tson.yd.model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "是否注销实体类")
public class LoginRegisterEntity extends LoginUserIdEntity {

    @ApiModelProperty(value = "是否注销")
    private Integer unRegister;

    public Integer getUnRegister() {
        return unRegister;
    }

    public void setUnRegister(Integer unRegister) {
        this.unRegister = unRegister;
    }
}
