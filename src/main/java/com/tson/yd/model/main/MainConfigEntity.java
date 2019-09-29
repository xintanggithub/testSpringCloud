package com.tson.yd.model.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "首页配置实体类")
public class MainConfigEntity {

    @ApiModelProperty(value = "配置1")
    private String content1;

    @ApiModelProperty(value = "配置2")
    private String content2;

    @ApiModelProperty(value = "配置3")
    private String content3;

    @ApiModelProperty(value = "配置4")
    private String content4;

    @ApiModelProperty(value = "配置5")
    private String content5;

    @ApiModelProperty(value = "配置6")
    private String content6;

    @ApiModelProperty(value = "配置7")
    private String content7;

    @ApiModelProperty(value = "配置8")
    private String content8;

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public String getContent5() {
        return content5;
    }

    public void setContent5(String content5) {
        this.content5 = content5;
    }

    public String getContent6() {
        return content6;
    }

    public void setContent6(String content6) {
        this.content6 = content6;
    }

    public String getContent7() {
        return content7;
    }

    public void setContent7(String content7) {
        this.content7 = content7;
    }

    public String getContent8() {
        return content8;
    }

    public void setContent8(String content8) {
        this.content8 = content8;
    }
}
