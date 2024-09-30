package com.y.gui.elasticsearch.service;

import com.y.gui.elasticsearch.dto.ESOrderDTO;
import com.y.gui.elasticsearch.param.EsQueryParam;
import com.y.gui.vo.PageParentVo;

public interface EsService {
    /**
     * 分页查询
     * @param param 分页请求参数
     * @return 查询的分页数据
     */
    PageParentVo<ESOrderDTO> queryPageList(EsQueryParam param);
}
