package com.javasoso.pass.constant;

/**
 * 平台枚举
 * 平台 1 网页 2 android 3 ios 4 浏览器插件 5 桌面
 */
public enum PlatformEnum {
    WEB(1,"网页"),
    ANDROID(2,"安卓手机"),
    IOS(3,"苹果手机"),
    PLUG(4,"浏览器插件"),
    DESKTOP(5,"桌面应用");
    private Integer code;
    private String desc;

    PlatformEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 根据code获取枚举
     * @param code
     * @return
     */
    public static PlatformEnum getEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (PlatformEnum value : PlatformEnum.values()) {
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
