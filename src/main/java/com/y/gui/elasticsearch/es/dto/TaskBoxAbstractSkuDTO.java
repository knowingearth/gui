package com.y.gui.elasticsearch.es.dto;

import lombok.Data;

@Data
public class TaskBoxAbstractSkuDTO {
    /**
     * skuId
     */
    private String skuId;

    /**
     * goodsNo
     */
    private String goodsNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 供货商ID
     */
    private String supplierId;

    /**
     * 供货商名称
     */
    private String supplierName;

    /**
     * 货品类型
     */
    private String goodsType;

    /**
     * 商品一级分类
     */
    private String categoryOne;

    /**
     * 商品二级分类
     */
    private String categoryTwo;

    /**
     * 商品三级分类
     */
    private String categoryThree;

    /**
     * 商品一级分类名称
     */
    private String categoryOneName;

    /**
     * 商品二级分类名称
     */
    private String categoryTwoName;

    /**
     * 商品三级分类名称
     */
    private String categoryThreeName;
}
