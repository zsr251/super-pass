package com.javasoso.pass.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 邮箱 修改密码时二次验证
     */
    private String email;
    /**
     * 手机号 修改密码时二次验证
     */
    private String phone;
}
