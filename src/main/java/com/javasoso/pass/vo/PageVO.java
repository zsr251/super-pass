package com.javasoso.pass.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页数据
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Accessors(chain = true)
@Data
public class PageVO<T> {
    /**
     * 数据列表
     */
    List<T> list;
    /**
     * 当前页数
     */
    Integer pageNum;
    /**
     * 每页个数
     */
    Integer pageSize;
    /**
     * 总页数
     */
    Integer totalPage;

}
