package com.javasoso.pass.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * 备忘录
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Accessors(chain = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemoVO {
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 标题
     */
    private String showTitle;

    /**
     * 内容密文 800字符
     */
    @NotEmpty(message = "内容不能为空")
    private String content;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 正常
     */
    private Integer status;
}
