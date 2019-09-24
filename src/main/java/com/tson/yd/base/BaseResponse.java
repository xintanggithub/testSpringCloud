package com.tson.yd.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "实体基类")
public class BaseResponse<T> {

    @ApiModelProperty(value = "返回码")
    private int resultCode;
    @ApiModelProperty(value = "返回信息")
    private String resultMessage;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(LogCode logCode) {
        setResultCode(logCode.getCode());
        setResultMessage(logCode.getMessage());
    }
}
