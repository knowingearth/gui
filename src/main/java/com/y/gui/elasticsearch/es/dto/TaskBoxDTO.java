package com.y.gui.elasticsearch.es.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class TaskBoxDTO {
    /**
     * 系统标识：SCM OMS PROMOTION GOODS
     */
    private String sysTag;

    /**
     * 流程ID
     */
    private String processId;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务key
     */
    private String taskKey;

    /**
     * 任务名称，和flowable中的任务名一致
     */
    private String taskName;

    /**
     * 任务的中文名，用于UI展现
     */
    private String taskChname;

    /**
     * 任务摘要，包括任务关联的订单编号、运单号、订单类型、商品编号(多个)，SKU编号(多个)
     */
    private String taskAbstract;

    /**
     * 指派人，接收此任务的人。系统用户的ID，在管理门户就是员工工号
     */
    private String assignee;

    /**
     * 任务创建的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String taskCreateTime;

    /**
     * 任务被接收的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String taskAssignTime;

    /**
     * 任务完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String taskCompleteTime;

    /**
     * 任务需要展示的数据，JSON格式
     */
    private String taskShowData;

    /**
     * 任务处理后传输给任务所属中台的结果
     */
    private String taskProcData;

    /**
     * 创建记录的操作员ID
     */
    private String insertId;

    /**
     * 记录最后的修改人ID
     */
    private String lastModifyId;

    /**
     * 任务状态：01待接收、02待处理、03处理结束
     */
    private String taskStatus;

    /**
     * 结构化任务摘要
     */
    private TaskBoxAbstractDTO tAbstract;
}
