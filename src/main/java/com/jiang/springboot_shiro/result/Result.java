package com.jiang.springboot_shiro.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="返回对象",description="")
public class Result<T> {
    @ApiModelProperty(value="状态码",name="code",example="状态码")
    private ResultCodeEnum code;
    @ApiModelProperty(value="状态信息",name="message",example="状态信息")
    private String message;
    @ApiModelProperty(value="具体描述",name="data",example="具体描述")
    private  T data;

    public ResultCodeEnum getCode() {
        return code;
    }

    public Result() {
    }

    public Result setCode(ResultCodeEnum resultCode) {
        this.code = resultCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}
