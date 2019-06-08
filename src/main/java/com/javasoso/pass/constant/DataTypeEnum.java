package com.javasoso.pass.constant;

/**
 * 数据类型枚举
 * 数据类型 1 应用数据 2 卡片数据 3 备忘录
 */
public enum DataTypeEnum {
    APP(1,"应用数据"),
    CARD(2,"卡片数据"),
    MEMO(3,"备忘录");
    private Integer code;
    private String desc;

    DataTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static DataTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (DataTypeEnum value : DataTypeEnum.values()) {
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
