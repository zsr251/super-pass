package com.javasoso.pass.constant;

/**
 * 登录方式枚举
 * 登录方式 1 密码 2 手机 3 邮箱 4 指纹
 */
public enum LoginTypeEnum {
    PASSWORD(1,"密码"),
    PHONE(2,"手机"),
    EMAIL(3,"邮箱"),
    FINGERMARK(4,"指纹");
    private Integer code;
    private String desc;

    LoginTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static LoginTypeEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (LoginTypeEnum value : LoginTypeEnum.values()) {
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
