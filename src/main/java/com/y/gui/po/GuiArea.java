package com.y.gui.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class GuiArea {
    /**
     * 主键
     */
    private Long id;

    /**
     * 区划编码
     */
    private Long code;

    /**
     * 父级编码
     */
    private Long parentCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 经度
     */
    private Float longitude;

    /**
     * 纬度
     */
    private Float latitude;

    /**
     * 等级(1省/直辖市,2地级市,3区县,4镇/街道,5居委会)
     */
    private Integer level;

    /**
     * 城乡分类代码：城乡分类代码以1开头的表示是城镇，以2开头表示是乡村。具体含义：111表示主城区，112表示城乡结合区，121表示镇中心区，122表示镇乡结合区，123表示特殊区域；210表示乡中心区，220表示村庄。
     */
    private Integer urCode;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(0禁用/1启用)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}