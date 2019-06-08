package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_memo")
public class Memo {
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 标题
     */
    @Column(name = "show_title")
    private String showTitle;

    /**
     * 内容密文 800字符
     */
    private String content;

    /**
     * 状态 -1 永久删除 0 废纸篓 1 正常
     */
    private Integer status;

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
     * 获取标题
     *
     * @return show_title - 标题
     */
    public String getShowTitle() {
        return showTitle;
    }

    /**
     * 设置标题
     *
     * @param showTitle 标题
     */
    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle == null ? null : showTitle.trim();
    }

    /**
     * 获取内容密文 800字符
     *
     * @return content - 内容密文 800字符
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容密文 800字符
     *
     * @param content 内容密文 800字符
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
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