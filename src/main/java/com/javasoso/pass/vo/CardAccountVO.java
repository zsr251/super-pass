package com.javasoso.pass.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 卡片类
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Accessors(chain = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardAccountVO {
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
     * 卡片名
     */
    private String cardName;

    /**
     * 名称
     */
    private String showName;

    /**
     * 展示的卡号
     */
    @NotEmpty(message = "展示不能为空")
    private String showCardNo;

    /**
     * 卡号密文
     */
    @NotEmpty(message = "卡号不能为空")
    private String cardNo;

    /**
     * 密码密文
     */
    private String password;

    /**
     * 备注 最多100字
     */
    private String remark;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 正常
     */
    private Integer status;
}
