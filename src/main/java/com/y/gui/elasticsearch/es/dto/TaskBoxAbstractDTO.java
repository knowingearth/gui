package com.y.gui.elasticsearch.es.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskBoxAbstractDTO {
    /**
     * 销售订单ID
     */
    private String orderId;

    /**
     * 售后单ID
     */
    private String offSaleOrderId;

    /**
     * SCM的销售发货单ID
     */
    private String delOrderId;

    /**
     * SCM的退货售后单ID
     */
    private String rtnOrderId;

    /**
     * 运单号
     */
    private String expressOrderId;

    /**
     * 采购单编号
     */
    private String purOrderId;

    /**
     * SCM的采购收货单编号
     */
    private String preOrderId;

    /**
     * 退供单编号
     */
    private String prtvOrderId;

    /**
     * SCM的退供发货单编号
     */
    private String rtvOrderId;

    /**
     * 仓库编号
     */
    private String warehouseCode;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 促销活动ID
     */
    private String promotionId;

    /**
     * 促销活动名称
     */
    private String promotionName;

    /**
     * 促销活动类型
     */
    private String promotionType;

    /**
     * 促销活动覆盖的部门
     */
    private String promotionDeptId;

    /**
     * 促销活动覆盖的渠道
     */
    private String promotionChannel;

    /**
     * 促销活动是否全覆盖
     */
    private Boolean promotionAll;

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
     * mdId
     */
    private String mdId;

    /**
     * md名称
     */
    private String mdName;

    /**
     * 商品信息
     */
    private List<TaskBoxAbstractSkuDTO> skus;
}
