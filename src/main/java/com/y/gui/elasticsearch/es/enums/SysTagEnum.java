package com.y.gui.elasticsearch.es.enums;

import org.springframework.util.StringUtils;

public enum SysTagEnum {
    SCM,
    OMS,
    PROMOTION,
    GOODS;

    public static SysTagEnum getSysTagEnum(String code) {
        if (!StringUtils.hasLength(code)) {
            return null;
        }
        for (SysTagEnum t : values()) {
            if (t.name().equals(code)) {
                return t;
            }
        }
        return null;
    }
}
