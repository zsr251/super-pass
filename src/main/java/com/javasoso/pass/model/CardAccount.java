package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_card_account")
public class CardAccount {
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
     * 卡片名
     */
    @Column(name = "card_name")
    private String cardName;

    /**
     * 名称
     */
    @Column(name = "show_name")
    private String showName;

    /**
     * 展示的卡号
     */
    @Column(name = "show_card_no")
    private String showCardNo;

    /**
     * 卡号密文
     */
    @Column(name = "card_no")
    private String cardNo;

    /**
     * 密码密文
     */
    private String password;

    /**
     * 备注 最多100字
     */
    private String remark;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 正常
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
     * 获取卡片名
     *
     * @return card_name - 卡片名
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * 设置卡片名
     *
     * @param cardName 卡片名
     */
    public void setCardName(String cardName) {
        this.cardName = cardName == null ? null : cardName.trim();
    }

    /**
     * 获取名称
     *
     * @return show_name - 名称
     */
    public String getShowName() {
        return showName;
    }

    /**
     * 设置名称
     *
     * @param showName 名称
     */
    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    /**
     * 获取展示的卡号
     *
     * @return show_card_no - 展示的卡号
     */
    public String getShowCardNo() {
        return showCardNo;
    }

    /**
     * 设置展示的卡号
     *
     * @param showCardNo 展示的卡号
     */
    public void setShowCardNo(String showCardNo) {
        this.showCardNo = showCardNo == null ? null : showCardNo.trim();
    }

    /**
     * 获取卡号密文
     *
     * @return card_no - 卡号密文
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 设置卡号密文
     *
     * @param cardNo 卡号密文
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
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
     * 获取备注 最多100字
     *
     * @return remark - 备注 最多100字
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注 最多100字
     *
     * @param remark 备注 最多100字
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取状态 -1 永久删除 0 废纸篓 1 正常
     *
     * @return status - 状态 -1 永久删除 0 废纸篓 1 正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 -1 永久删除 0 废纸篓 1 正常
     *
     * @param status 状态 -1 永久删除 0 废纸篓 1 正常
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