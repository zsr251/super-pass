package com.javasoso.pass.service;

import com.github.pagehelper.PageInfo;
import com.javasoso.pass.model.AppAccount;
import com.javasoso.pass.vo.AppAccountVO;

/**
 * 账户类服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
public interface AppService {
    /**
     * 增加账户类
     * @param appAccountVO
     * @param ip
     * @return
     */
    AppAccount addAppAccount(AppAccountVO appAccountVO,String ip);

    /**
     * 修改账户类
     * @param appAccountVO
     * @param ip
     * @return
     */
    boolean updateAppAccount(AppAccountVO appAccountVO,String ip);

    /**
     * 删除账户类
     * @param id
     * @param ip
     * @return
     */
    boolean deleteAppAccount(Integer id,String ip);

    /**
     * 分页搜索
     * @param appAccountVO
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AppAccount> searchByPage(AppAccountVO appAccountVO,Integer pageNum,Integer pageSize);

    /**
     * 根据ID搜索
     * @param id
     * @param ip
     * @return
     */
    AppAccount searchById(Integer id,String ip);

}
