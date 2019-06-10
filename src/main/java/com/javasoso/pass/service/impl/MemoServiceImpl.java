package com.javasoso.pass.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javasoso.pass.aop.CheckLoginAspect;
import com.javasoso.pass.constant.DataTypeEnum;
import com.javasoso.pass.constant.OperateTypeEnum;
import com.javasoso.pass.exception.SuperPassException;
import com.javasoso.pass.mapper.MemoMapper;
import com.javasoso.pass.mapper.OperateLogMapper;
import com.javasoso.pass.model.Memo;
import com.javasoso.pass.model.OperateLog;
import com.javasoso.pass.service.MemoService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.vo.MemoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 备忘录
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class MemoServiceImpl implements MemoService {
    @Autowired
    private MemoMapper memoMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public Memo addMemo(MemoVO memoVO, String ip) {
        Memo record = BeanUtil.copyBean(memoVO,Memo.class);
        record.setUserId(CheckLoginAspect.getCurrentUser().getId());
        record.setStatus(1);
        record.setCreateTime(new Date());
        memoMapper.insertUseGeneratedKeys(record);
        log.info("用户【{}】新增一条备忘录数据",record.getUserId());

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.MEMO.getCode());
        operateLog.setOperateType(OperateTypeEnum.APPEND.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("新增备忘录信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);
        return record;
    }

    @Override
    public boolean updateMemo(MemoVO memoVO, String ip) {
        if (memoVO == null || memoVO.getId() == null){
            throw new SuperPassException("参数异常");
        }
        memoVO.setStatus(null);
        Memo record = memoMapper.selectByPrimaryKey(memoVO.getId());
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = BeanUtil.copyBean(memoVO,Memo.class);
        memoMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.MEMO.getCode());
        operateLog.setOperateType(OperateTypeEnum.UPDATE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("修改备忘录信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public boolean deleteMemo(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        Memo record = memoMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        record = new Memo();
        record.setId(id);
        record.setStatus(0);
        memoMapper.updateByPrimaryKeySelective(record);

        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.MEMO.getCode());
        operateLog.setOperateType(OperateTypeEnum.DELETE.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("删除备忘录信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return true;
    }

    @Override
    public PageInfo<Memo> searchByPage(MemoVO memoVO, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null || pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize == null || pageSize < 1 ? 10 : pageSize;
        memoVO.setUserId(CheckLoginAspect.getCurrentUser().getId());
        Memo record = BeanUtil.copyBean(memoVO,Memo.class);
        PageHelper.startPage(pageNum,pageSize);
        List<Memo> appAccountList = memoMapper.select(record);
        return new PageInfo<>(appAccountList);
    }

    @Override
    public Memo searchById(Integer id, String ip) {
        if (id == null){
            throw new SuperPassException("参数异常");
        }
        Memo record = memoMapper.selectByPrimaryKey(id);
        if (record == null){
            throw new SuperPassException("未找到该数据");
        }
        if (!record.getUserId().equals(CheckLoginAspect.getCurrentUser().getId())){
            throw new SuperPassException("非本人数据");
        }
        OperateLog operateLog = new OperateLog();
        operateLog.setDataType(DataTypeEnum.MEMO.getCode());
        operateLog.setOperateType(OperateTypeEnum.VIEW.getCode());
        operateLog.setPlatform(CheckLoginAspect.getCurrentPlatform().getCode());
        operateLog.setUserId(CheckLoginAspect.getCurrentUser().getId());
        operateLog.setRemark("查看备忘录信息");
        operateLog.setIp(ip);
        operateLog.setDataId(record.getId());
        operateLogMapper.insertSelective(operateLog);

        return record;
    }
}
