package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_app_account")
public class AppAccount {
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 应用名
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 账户名
     */
    @Column(name = "show_user_name")
    private String showUserName;

    /**
     * 密码密文
     */
    private String password;

    /**
     * URL
     */
    private String url;

    /**
     * 备注最多100字
     */
    private String remark;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 有效
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
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
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取应用名
     *
     * @return app_name - 应用名
     */
    public String getAppName() {
        return appName;
    }

    /**
     * 设置应用名
     *
     * @param appName 应用名
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * 获取账户名
     *
     * @return show_user_name - 账户名
     */
    public String getShowUserName() {
        return showUserName;
    }

    /**
     * 设置账户名
     *
     * @param showUserName 账户名
     */
    public void setShowUserName(String showUserName) {
        this.showUserName = showUserName == null ? null : showUserName.trim();
    }

    /**
     * 获取密码密文
     *
     * @return password - 密码密文
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码密文
     *
     * @param password 密码密文
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取URL
     *
     * @return url - URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取备注最多100字
     *
     * @return remark - 备注最多100字
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注最多100字
     *
     * @param remark 备注最多100字
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取状态 -1 永久删除 0 废纸篓 1 有效
     *
     * @return status - 状态 -1 永久删除 0 废纸篓 1 有效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 -1 永久删除 0 废纸篓 1 有效
     *
     * @param status 状态 -1 永久删除 0 废纸篓 1 有效
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}