package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_show_type")
public class ShowType {
    private Integer id;

    /**
     * 0 账户类 1 卡类
     */
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * 图标标示
     */
    private String icon;

    /**
     * 应用名
     */
    @Column(name = "default_name")
    private String defaultName;

    /**
     * URL
     */
    @Column(name = "default_url")
    private String defaultUrl;

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
     * 获取0 账户类 1 卡类
     *
     * @return account_type - 0 账户类 1 卡类
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 设置0 账户类 1 卡类
     *
     * @param accountType 0 账户类 1 卡类
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * 获取图标标示
     *
     * @return icon - 图标标示
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标标示
     *
     * @param icon 图标标示
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取应用名
     *
     * @return default_name - 应用名
     */
    public String getDefaultName() {
        return defaultName;
    }

    /**
     * 设置应用名
     *
     * @param defaultName 应用名
     */
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName == null ? null : defaultName.trim();
    }

    /**
     * 获取URL
     *
     * @return default_url - URL
     */
    public String getDefaultUrl() {
        return defaultUrl;
    }

    /**
     * 设置URL
     *
     * @param defaultUrl URL
     */
    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl == null ? null : defaultUrl.trim();
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