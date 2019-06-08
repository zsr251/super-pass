package com.javasoso.pass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javasoso.pass.aop.CheckLoginAspect;
import com.javasoso.pass.constant.DataTypeEnum;
import com.javasoso.pass.constant.OperateTypeEnum;
import com.javasoso.pass.exception.SuperPassException;
import com.javasoso.pass.mapper.AppAccountMapper;
import com.javasoso.pass.mapper.OperateLogMapper;
import com.javasoso.pass.model.AppAccount;
import com.javasoso.pass.model.OperateLog;
import com.javasoso.pass.service.AppService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.vo.AppAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 账户类
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppAccountMapper appAccountMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public AppAccount addAppAccount(AppAccountVO appAccountVO, String ip) {
        AppAccount record = BeanUtil.copyBean(appAccountVO,AppAccount.class);
        record.setUserId(CheckLoginAspect.getCurrentUser().getId());
        record.setStatus(1);
        record.setCreateTime(new Date());
        appAccountMapper.insertUseGeneratedKeys(record);
        log.info("用户【{}】新增一条账户类数据",record.getUserId());

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.APP.getCode());
        operateLog.setOperateType(OperateTypeEnum.APPEND.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("新增账户类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);
        return record;
    }

    @Override
    public boolean updateAppAccount(AppAccountVO appAccountVO, String ip) {
        if (appAccountVO == null || appAccountVO.getId() == null){
            throw new SuperPassException("参数异常");
        }
        AppAccount record = appAccountMapper.selectByPrimaryKey(appAccountVO.getId());
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = BeanUtil.copyBean(appAccountVO,AppAccount.class);
        appAccountMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.APP.getCode());
        operateLog.setOperateType(OperateTypeEnum.UPDATE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("修改账户类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public boolean deleteAppAccount(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        AppAccount record = appAccountMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = new AppAccount();
        record.setId(id);
        record.setStatus(0);
        appAccountMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.APP.getCode());
        operateLog.setOperateType(OperateTypeEnum.DELETE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("删除账户类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public PageInfo<AppAccount> searchByPage(AppAccountVO appAccountVO, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize < 1 ? 10 : pageSize;
        appAccountVO.setUserId(CheckLoginAspect.getCurrentUser().getId());
        AppAccount record = BeanUtil.copyBean(appAccountVO,AppAccount.class);
        PageHelper.startPage(pageNum,pageSize);
        List<AppAccount> appAccountList = appAccountMapper.select(record);
        return new PageInfo<>(appAccountList);
    }

    @Override
    public AppAccount searchById(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        AppAccount record = appAccountMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.APP.getCode());
        operateLog.setOperateType(OperateTypeEnum.VIEW.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("查看账户类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return record;
    }
}
