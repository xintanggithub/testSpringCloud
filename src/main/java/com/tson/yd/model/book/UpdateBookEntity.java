package com.tson.yd.model.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "更新book实体")
public class UpdateBookEntity {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "封面 1 精  2 普通")
    private String splash;

    @ApiModelProperty(value = "tag")
    private String img;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "是否公开")
    private Integer openType;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "类型")
    private String bookType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Integer getOpenType() {
        return openType;
    }

    public void setOpenType(Integer openType) {
        this.openType = openType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSplash() {
        return splash;
    }

    public void setSplash(String splash) {
        this.splash = splash;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
