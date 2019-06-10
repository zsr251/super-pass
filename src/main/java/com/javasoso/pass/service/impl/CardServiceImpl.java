package com.javasoso.pass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javasoso.pass.aop.CheckLoginAspect;
import com.javasoso.pass.constant.DataTypeEnum;
import com.javasoso.pass.constant.OperateTypeEnum;
import com.javasoso.pass.exception.SuperPassException;
import com.javasoso.pass.mapper.CardAccountMapper;
import com.javasoso.pass.mapper.OperateLogMapper;
import com.javasoso.pass.model.CardAccount;
import com.javasoso.pass.model.CardAccount;
import com.javasoso.pass.model.OperateLog;
import com.javasoso.pass.service.CardService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.vo.CardAccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 卡片类
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardAccountMapper cardAccountMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public CardAccount addCardAccount(CardAccountVO cardAccountVO, String ip) {
        CardAccount record = BeanUtil.copyBean(cardAccountVO,CardAccount.class);
        record.setUserId(CheckLoginAspect.getCurrentUser().getId());
        record.setStatus(1);
        record.setCreateTime(new Date());
        cardAccountMapper.insertUseGeneratedKeys(record);
        log.info("用户【{}】新增一条卡片类数据",record.getUserId());

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.CARD.getCode());
        operateLog.setOperateType(OperateTypeEnum.APPEND.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("新增卡片类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);
        return record;
    }

    @Override
    public boolean updateCardAccount(CardAccountVO cardAccountVO, String ip) {
        if (cardAccountVO == null || cardAccountVO.getId() == null){
            throw new SuperPassException("参数异常");
        }
        cardAccountVO.setStatus(null);
        CardAccount record = cardAccountMapper.selectByPrimaryKey(cardAccountVO.getId());
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = BeanUtil.copyBean(cardAccountVO,CardAccount.class);
        cardAccountMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.CARD.getCode());
        operateLog.setOperateType(OperateTypeEnum.UPDATE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("修改卡片类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public boolean deleteCardAccount(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        CardAccount record = cardAccountMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = new CardAccount();
        record.setId(id);
        record.setStatus(0);
        cardAccountMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.CARD.getCode());
        operateLog.setOperateType(OperateTypeEnum.DELETE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("删除卡片类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public PageInfo<CardAccount> searchByPage(CardAccountVO cardAccountVO, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize < 1 ? 10 : pageSize;
        cardAccountVO.setUserId(CheckLoginAspect.getCurrentUser().getId());
        CardAccount record = BeanUtil.copyBean(cardAccountVO,CardAccount.class);
        PageHelper.startPage(pageNum,pageSize);
        List<CardAccount> appAccountList = cardAccountMapper.select(record);
        return new PageInfo<>(appAccountList);
    }

    @Override
    public CardAccount searchById(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        CardAccount record = cardAccountMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.CARD.getCode());
        operateLog.setOperateType(OperateTypeEnum.VIEW.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("查看卡片类信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return record;
    }
}
