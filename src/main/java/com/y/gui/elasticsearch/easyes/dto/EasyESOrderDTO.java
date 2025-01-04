package com.y.gui.elasticsearch.easyes.dto;

import lombok.Data;

import java.util.List;

@Data
//@IndexName(value = "scm_order", maxResultWindow = 100000)
public class EasyESOrderDTO {
    /**
     * SCM订单号
     * IdType.NONE: 由ES自动生成,是默认缺省时的配置
     * IdType.UUID: 系统生成UUID,然后插入ES (不推荐)
     * IdType.CUSTOMIZE: 由用户自定义,用户自己对id值进行set：
     *     如果用户指定的id在es中不存在,则在insert时就会新增一条记录；
     *     如果用户指定的id在es中已存在记录,则自动更新该id对应的记录.
     */
//    @IndexId(type = IdType.CUSTOMIZE)
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.KEYWORD, ignoreCase = true)
    private String id;

    /**
     * 订单号(OMS订单号)
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.KEYWORD, ignoreCase = true)
    private String saleOrderId;

//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String orderType;

//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String orderTypeStr;

    /**
     * 状态码
     * 不同的单据类型状态不一样
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String status;

    /**
     * 状态
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String statusStr;

    /**
     * 仓库编码
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String warehouseCode;

    /**
     * 仓库名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String warehouseName;

    /**
     * WMS系统-仓库单号
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String wmsReceiptsId;

    /**
     * 快递公司ID
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String logisticCompanyId;

    /**
     * 快递公司名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String logisticCompanyName;

    /**
     * 快递账号ID（中台）
     */
//    @IndexField(strategy = FieldStrategy.DEFAULT)
    private String logisticAccountId;

    /**
     * 快递账号名称
     */
//    @IndexField(strategy = FieldStrategy.DEFAULT)
    private String logisticAccountName;

    /**
     * 运单号
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String expressCode;

    /**
     * 供应商编码
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String vendorId;

    /**
     * 供应商名称
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY)
    private String vendorName;

    /**
     * 创建时间
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    /**
     * 修改时间
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.DATE, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String modifyTime;

    /**
     * 单据中的商品信息
     */
//    @IndexField(strategy = FieldStrategy.NOT_EMPTY, fieldType = FieldType.NESTED, nestedClass = ESOrderItemDTO.class)
    private List<EasyESOrderItemDTO> orderItems;
}
