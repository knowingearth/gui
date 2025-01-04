package com.y.gui.elasticsearch.easyes.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.y.gui.param.PageParentParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class EasyEsQueryParam extends PageParentParam {
    @Schema(
            title = "saleOrderId",
            description = "订单号",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "SAL24052813456"
    )
    private String saleOrderId;

    @Schema(
            title = "scmOrderId",
            description = "配送单号",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "SO20240325001028"
    )
    private String scmOrderId;

    @Schema(
            title = "spuCode",
            description = "商品编码",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "羽绒服"
    )
    private String spuCode;

    @Schema(
            title = "itemName",
            description = "商品名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "羽绒服"
    )
    private String itemName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(
            title = "createDateStart",
            description = "创建时间Start",
            format = "LocalDate",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-06-01"
    )
    private Date createDateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(
            title = "createDateEnd",
            description = "创建时间End",
            format = "LocalDate",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-06-02"
    )
    private Date createDateEnd;
}
