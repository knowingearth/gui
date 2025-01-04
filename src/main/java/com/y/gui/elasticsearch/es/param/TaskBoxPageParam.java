package com.y.gui.elasticsearch.es.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.y.gui.elasticsearch.es.enums.SysTagEnum;
import com.y.gui.param.PageParentParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "查询任务箱分页列表参数")
public class TaskBoxPageParam extends PageParentParam {
    @Schema(
            title = "由我处理的任务",
            description = "是否查询由我处理的任务",
            format = "Boolean",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "true"
    )
    private Boolean queryMineTask;

    @Schema(
            title = "任务ID",
            description = "任务ID",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "00067131-a447-11ef-83a1-20040fe7a510"
    )
    private String taskId;

    @Schema(
            title = "相关单据ID",
            description = "相关单据ID",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "SAL019203334"
    )
    private String relevanceOrderId;

    @Schema(
            title = "促销活动",
            description = "促销活动ID|名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "SAL019203334"
    )
    private String promotion;

    @Schema(
            title = "商品",
            description = "商品ID|SKU|商品名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "139225001"
    )
    private String goods;

    @Schema(
            title = "md",
            description = "md ID|名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "139225001"
    )
    private String md;

    @Schema(
            title = "供货商",
            description = "供货商 ID|名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "139225001"
    )
    private String supplier;

    @Schema(
            title = "任务Key",
            description = "任务Key",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "task_PurchaseQC"
    )
    private String taskKey;

    @Schema(
            title = "任务名称",
            description = "任务名称",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "139225001"
    )
    private String taskName;

    @Schema(
            title = "所属系统",
            description = "系统标识：SCM OMS PROMOTION GOODS",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "SCM"
    )
    private SysTagEnum sysTag;

    @Schema(
            title = "状态",
            description = "任务状态：01待接收、02待处理、03处理结束",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "01"
    )
    private String taskStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务创建的时间",
            description = "任务创建的时间开始",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCreateTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务创建的时间",
            description = "任务创建的时间结束",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCreateTimeEnd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务被接收的时间",
            description = "任务被接收的时间开始",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskAssignTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务被接收的时间",
            description = "任务被接收的时间结束",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskAssignTimeEnd;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务完成时间",
            description = "任务完成时间开始",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCompleteTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "任务完成时间",
            description = "任务完成时间结束",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCompleteTimeEnd;

    /**
     * 排序列名(空采用接口默认排序)
     */
    @Schema(
            title = "sortColumn",
            description = "排序列名(空采用接口默认排序)",
            format = "string"
    )
    private String sortColumn;
}
