package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_login_log")
public class LoginLog {
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 登录方式 1 密码 2 手机 3 邮箱 4 指纹
     */
    @Column(name = "login_type")
    private Integer loginType;

    /**
     * 平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     */
    private Integer platform;

    /**
     * 客户端数据
     */
    private String agent;

    /**
     * 登录IP
     */
    private String ip;

    /**
     * 登录token
     */
    private String token;

    /**
     * 0 登录失败 1 登录成功
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取登录方式 1 密码 2 手机 3 邮箱 4 指纹
     *
     * @return login_type - 登录方式 1 密码 2 手机 3 邮箱 4 指纹
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * 设置登录方式 1 密码 2 手机 3 邮箱 4 指纹
     *
     * @param loginType 登录方式 1 密码 2 手机 3 邮箱 4 指纹
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * 获取平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     *
     * @return platform - 平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 设置平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     *
     * @param platform 平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 获取客户端数据
     *
     * @return agent - 客户端数据
     */
    public String getAgent() {
        return agent;
    }

    /**
     * 设置客户端数据
     *
     * @param agent 客户端数据
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * 获取登录IP
     *
     * @return ip - 登录IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置登录IP
     *
     * @param ip 登录IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取登录token
     *
     * @return token - 登录token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置登录token
     *
     * @param token 登录token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取0 登录失败 1 登录成功
     *
     * @return status - 0 登录失败 1 登录成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0 登录失败 1 登录成功
     *
     * @param status 0 登录失败 1 登录成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}