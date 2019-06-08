package com.javasoso.pass.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 默认展示
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Accessors(chain = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowTypeVO {
    /**
     * 1 账户类 2 卡类
     */
    private Integer accountType;

    /**
     * 图标标示
     */
    private String icon;

    /**
     * 应用名
     */
    private String defaultName;

    /**
     * URL
     */
    private String defaultUrl;
}
