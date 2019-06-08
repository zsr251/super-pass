package com.javasoso.pass.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_operate_log")
public class OperateLog {
    @Id
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
     */
    private Integer platform;

    /**
     * 操作IP
     */
    private String ip;

    /**
     * 操作类型 1 查看 2 新增 3 修改 4 删除
     */
    @Column(name = "operate_type")
    private Integer operateType;

    /**
     * 数据类型 1 应用数据 2 卡片数据 3 备忘录
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 记录ID
     */
    @Column(name = "data_id")
    private Integer dataId;

    /**
     * 操作备注
     */
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取平台 1 网页 2 android 3 ios 4 桌面
     *
     * @return platform - 平台 1 网页 2 android 3 ios 4 桌面
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 设置平台 1 网页 2 android 3 ios 4 桌面
     *
     * @param platform 平台 1 网页 2 android 3 ios 4 桌面
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 获取操作IP
     *
     * @return ip - 操作IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置操作IP
     *
     * @param ip 操作IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取操作类型 1 查看 2 新增 3 修改 4 删除
     *
     * @return operate_type - 操作类型 1 查看 2 新增 3 修改 4 删除
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型 1 查看 2 新增 3 修改 4 删除
     *
     * @param operateType 操作类型 1 查看 2 新增 3 修改 4 删除
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取数据类型 1 应用数据 2 卡片数据 3 备忘录
     *
     * @return data_type - 数据类型 1 应用数据 2 卡片数据 3 备忘录
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型 1 应用数据 2 卡片数据 3 备忘录
     *
     * @param dataType 数据类型 1 应用数据 2 卡片数据 3 备忘录
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取记录ID
     *
     * @return data_id - 记录ID
     */
    public Integer getDataId() {
        return dataId;
    }

    /**
     * 设置记录ID
     *
     * @param dataId 记录ID
     */
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    /**
     * 获取操作备注
     *
     * @return remark - 操作备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置操作备注
     *
     * @param remark 操作备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}