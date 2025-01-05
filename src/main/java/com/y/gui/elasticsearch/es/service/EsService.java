package com.y.gui.elasticsearch.es.service;

import com.y.gui.elasticsearch.es.dto.TaskBoxDTO;
import com.y.gui.elasticsearch.es.param.TaskBoxPageParam;
import com.y.gui.elasticsearch.es.vo.TaskBoxPageVO;
import com.y.gui.vo.PageParentVo;

public interface EsService {
    /**
     * 分页查询任务箱
     * @param param 查询条件
     * @return 任务箱数据
     */
    PageParentVo<TaskBoxPageVO> queryTaskBoxPage(TaskBoxPageParam param, String userId);

    /**
     * 获取任务详情
     * @param taskId 任务ID
     * @return 任务详情
     */
    TaskBoxDTO queryTaskBoxInfo(String taskId);
}
