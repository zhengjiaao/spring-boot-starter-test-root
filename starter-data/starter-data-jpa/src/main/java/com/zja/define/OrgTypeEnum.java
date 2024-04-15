package com.zja.define;

import org.springframework.util.StringUtils;

/**
 * 机构类型
 *
 * @author: zhengja
 * @since: 2023/08/09 15:11
 */
public enum OrgTypeEnum {

    REGION(1, "行政区划"),
    ORGANIZATION(2, "行政单位"),
    DEPARTMENT(3, "内部机构");

    final int code;
    final String desc;

    OrgTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public static OrgTypeEnum getCodeByDesc(String desc) {
        if (!StringUtils.hasLength(desc)) {
            return null;
        }

        for (OrgTypeEnum value : values()) {
            if (value.desc.equals(desc)) {
                return value;
            }
        }
        return null;
    }
}
