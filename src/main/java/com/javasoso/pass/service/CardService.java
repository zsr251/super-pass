package com.javasoso.pass.service;

import com.github.pagehelper.PageInfo;
import com.javasoso.pass.model.CardAccount;
import com.javasoso.pass.vo.CardAccountVO;

/**
 * 卡片类服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface CardService {
    /**
     * 增加卡片类
     * @param cardAccountVO
     * @param ip
     * @return
     */
    CardAccount addCardAccount(CardAccountVO cardAccountVO, String ip);

    /**
     * 修改卡片类
     * @param cardAccountVO
     * @param ip
     * @return
     */
    boolean updateCardAccount(CardAccountVO cardAccountVO,String ip);

    /**
     * 删除卡片类
     * @param id
     * @param ip
     * @return
     */
    boolean deleteCardAccount(Integer id,String ip);

    /**
     * 分页搜索
     * @param cardAccountVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<CardAccount> searchByPage(CardAccountVO cardAccountVO, Integer pageNum, Integer pageSize);

    /**
     * 根据ID搜索
     * @param id
     * @param ip
     * @return
     */
    CardAccount searchById(Integer id,String ip);
}
