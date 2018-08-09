package com.jiang.springboot_shiro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(value="user对象",description="用户对象user")
public class User  implements Serializable{
    @NotNull(message = "用户名不为空")
    @Size(min=2, max=30)
    @ApiModelProperty(value="用户名",name="name",example="用户名")
    private String name;
    @NotNull(message = "密码不为空")
    @Size(min=4, max=12)
    @ApiModelProperty(value="密码",name="password",example="密码")
    private String password;
    @ApiModelProperty(value="权限",name="authority",example="权限")
    private String authority;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
