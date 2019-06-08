package com.javasoso.pass.service;

import com.github.pagehelper.PageInfo;
import com.javasoso.pass.model.Memo;
import com.javasoso.pass.vo.MemoVO;

/**
 * 备忘录服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface MemoService {
    /**
     * 增加备忘录
     * @param memoVO
     * @param ip
     * @return
     */
    Memo addMemo(MemoVO memoVO, String ip);

    /**
     * 修改备忘录
     * @param memoVO
     * @param ip
     * @return
     */
    boolean updateMemo(MemoVO memoVO,String ip);

    /**
     * 删除备忘录
     * @param id
     * @param ip
     * @return
     */
    boolean deleteMemo(Integer id,String ip);

    /**
     * 分页搜索
     * @param memoVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Memo> searchByPage(MemoVO memoVO, Integer pageNum, Integer pageSize);

    /**
     * 根据ID搜索
     * @param id
     * @param ip
     * @return
     */
    Memo searchById(Integer id,String ip);
}
