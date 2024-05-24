package com.y.gui.common.utils.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 20种数据类型 枚举
 */
@Getter
@AllArgsConstructor
public enum DataTypeEnum {
    BJ(1, "北京地区数据"),
    TJ(2, "天津地区数据"),
    HB(3, "河北地区数据");
    private Integer type;
    private String desc;

    public static String getDescByType(Integer type) {
        if (null == type) {
            return null;
        }
        return Stream.of(values()).filter(e -> e.getType().equals(type)).findFirst().map(DataTypeEnum::getDesc).orElse(null);
    }

    public static List<DataTypeEnum> getAllEnums() {
        return Stream.of(values()).collect(Collectors.toList());
    }
}
