package com.javasoso.pass.mapper;

import com.javasoso.pass.model.LoginLog;
import com.javasoso.pass.util.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper extends MyMapper<LoginLog> {
    /**
     * 清理 token
     * @param userId
     * @param platform
     * @return
     */
    int clearToken(@Param("userId") Integer userId,@Param("platform") Integer platform);
}