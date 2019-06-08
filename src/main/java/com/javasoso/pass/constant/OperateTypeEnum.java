package com.javasoso.pass.constant;

/**
 * 操作类型枚举
 * 操作类型 1 查看 2 新增 3 修改 4 删除
 */
public enum OperateTypeEnum {
    VIEW(1,"查看"),
    APPEND(2,"新增"),
    UPDATE(3,"修改"),
    DELETE(4,"删除");
    private Integer code;
    private String desc;

    OperateTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static OperateTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (OperateTypeEnum value : OperateTypeEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
