package com.y.gui.elasticsearch.es.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.y.gui.elasticsearch.es.enums.SysTagEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class TaskBoxPageVO {
    @Schema(
            title = "sysTag",
            description = "系统标识：SCM OMS PROMOTION GOODS",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "SCM"
    )
    private SysTagEnum sysTag;

    @Schema(
            title = "processId",
            description = "流程ID",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "706831b7-a445-11ef-83a1-20040fe7a510"
    )
    private String processId;

    @Schema(
            title = "taskId",
            description = "任务ID",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "00067131-a447-11ef-83a1-20040fe7a510"
    )
    private String taskId;

    @Schema(
            title = "taskKey",
            description = "任务key",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "purchaseOrderTimeoutTask"
    )
    private String taskKey;

    @Schema(
            title = "taskName",
            description = "任务名称，和flowable中的任务名一致",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "人工处理采购入库超时"
    )
    private String taskName;

    @Schema(
            title = "taskChname",
            description = "任务的中文名，用于UI展现",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "人工处理采购入库超时"
    )
    private String taskChname;

    @Schema(
            title = "taskAbstract",
            description = "任务摘要，包括任务关联的订单编号、运单号、订单类型、商品编号(多个)，SKU编号(多个)",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "DEL24122409582373011"
    )
    private String taskAbstract;

    @Schema(
            title = "assignee",
            description = "指派人，接收此任务的人。系统用户的ID，在管理门户就是员工工号",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "098987"
    )
    private String assignee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "taskCreateTime",
            description = "任务创建的时间",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "taskAssignTime",
            description = "任务被接收的时间",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskAssignTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(
            title = "taskCompleteTime",
            description = "任务完成时间",
            format = "string",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2024-12-26 14:30:30"
    )
    private Date taskCompleteTime;

    @Schema(
            title = "taskStatus",
            description = "任务状态：01待接收、02待处理、03处理结束",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "01"
    )
    private String taskStatus;

    @Schema(
            title = "taskStatusStr",
            description = "任务状态：01待接收、02待处理、03处理结束",
            format = "string",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "待接收"
    )
    private String taskStatusStr;

    @Schema(
            title = "viewPermissions",
            description = "是否有查看权限",
            format = "Boolean",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "true"
    )
    private Boolean viewPermissions;

    @Schema(
            title = "dealPermissions",
            description = "是否有编辑权限",
            format = "Boolean",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "true"
    )
    private Boolean dealPermissions;
}
