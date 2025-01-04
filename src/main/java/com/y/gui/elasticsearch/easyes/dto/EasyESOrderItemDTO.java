package com.y.gui.elasticsearch.easyes.dto;

import lombok.Data;

@Data
public class EasyESOrderItemDTO {
    /**
     * 商品编号
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String spuCode;

    /**
     * 商品名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.KEYWORD, fieldData = true)
    private String itemName;

    /**
     * 一级商品分类名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String categoryOneName;

    /**
     * 二级商品分类名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String categoryTwoName;

    /**
     * 三级商品分类名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String categoryThreeName;

    /**
     * 一级商品分类标签
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String categoryOneLabel;

    /**
     * 二级商品分类标签
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String categoryTwoLabel;

    /**
     * 三级商品分类标签
     */
//    @IndexField(strategy = FieldStrategy.NOT_NULL)
    private String categoryThreeLabel;

    /**
     * 货品类型:01-普通,02-酒水,03-生鲜,04-冷运
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String goodsType;

    /**
     * 货品类型:01-普通,02-酒水,03-生鲜,04-冷运
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String goodsTypeStr;

//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String skuCode;

//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.TEXT, fieldData = true)
    private String skuInfo;
}
