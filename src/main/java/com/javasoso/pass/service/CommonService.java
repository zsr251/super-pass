package com.javasoso.pass.service;

import com.javasoso.pass.model.ShowType;
import com.javasoso.pass.vo.ShowTypeVO;

import java.util.List;

/**
 * 公用服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface CommonService {
    /**
     * 搜索默认图标
     * @param showTypeVO
     * @return
     */
    List<ShowType> searchShowType(ShowTypeVO showTypeVO);
}
