package com.tson.yd.model.header;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "头像")
public class HeadEntity {

    @ApiModelProperty(value = "头像")
    private String head;
    @ApiModelProperty(value = "类型 N/A")
    private String type;
    @ApiModelProperty(value = "备用字段 N/A")
    private String space;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }
}
