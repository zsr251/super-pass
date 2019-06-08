package com.javasoso.pass.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 应用账户密码
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppAccountVO {
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 账户名
     */
    @NotEmpty(message = "账户名不能为空")
    private String showUserName;

    /**
     * 密码密文
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * URL
     */
    private String url;

    /**
     * 备注最多100字
     */
    private String remark;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 有效
     */
    private Integer status;
}



