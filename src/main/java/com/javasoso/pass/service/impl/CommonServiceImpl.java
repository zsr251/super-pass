package com.javasoso.pass.service.impl;

import com.javasoso.pass.mapper.ShowTypeMapper;
import com.javasoso.pass.model.ShowType;
import com.javasoso.pass.service.CommonService;
import com.javasoso.pass.util.BeanUtil;
import com.javasoso.pass.vo.ShowTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共服务
 *
 * @author jasonzhu
 * @date 2019-06-08
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private ShowTypeMapper showTypeMapper;

    @Override
    public List<ShowType> searchShowType(ShowTypeVO showTypeVO) {
        ShowType record = BeanUtil.copyBean(showTypeVO,ShowType.class);
        return showTypeMapper.select(record);
    }
}
