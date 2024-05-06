package com.y.gui.po;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GuiArea {
    private Long id;

    private Long code;

    private Long parentCode;

    private String name;

    private String shortName;

    private Float longitude;

    private Float latitude;

    private Integer level;

    private Integer urCode;

    private Integer sort;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}