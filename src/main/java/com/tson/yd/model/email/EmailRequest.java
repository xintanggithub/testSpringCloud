package com.tson.yd.model.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户信息实体类")
public class EmailRequest {

    @ApiModelProperty(value = "收件人")
    private String to;
    @ApiModelProperty(value = "主题")
    private String subject;
    @ApiModelProperty(value = "内容")
    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
