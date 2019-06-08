package com.javasoso.pass.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 普通登录
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Data
public class LoginVO {
    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String superPass;
}
