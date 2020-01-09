package com.tson.yd.model.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "bookAll实体")
public class BookAllEntity extends BookEntity {

    @ApiModelProperty(value = "userName")
    private String userName;
    @ApiModelProperty(value = "userName")
    private String userHead;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }
}
