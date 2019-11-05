package com.tson.yd.model.game.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "game请求实体")
public class GameRequest {
    @ApiModelProperty(value = "标签，半角标逗号隔开，如 ： 电影,大电影,国庆档")
    private String tag;
    @ApiModelProperty(value = "图片路径配置如  assets/img/test")
    private String img;
    @ApiModelProperty(value = "跳转链接")
    private String html;
    @ApiModelProperty(value = "标签")
    private String title;
    @ApiModelProperty(value = "内容")
    private String content;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
