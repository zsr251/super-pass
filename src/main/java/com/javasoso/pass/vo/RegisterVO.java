package com.javasoso.pass.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 注册VO
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Accessors(chain = true)
@Data
public class RegisterVO {
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
