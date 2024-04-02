package com.y.gui.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(200, "成功"),
    UN_KNOW_ERROR(999, "未知异常");

    private Integer code;
    private String msg;
}
