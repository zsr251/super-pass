package com.javasoso.pass.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Accessors(chain = true)
@Data
public class UserVO {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    private String superPass;
    /**
     * 邮箱 修改密码时二次验证
     */
    private String email;
    /**
     * 手机号 修改密码时二次验证
     */
    private String phone;
}
