package com.y.gui.elasticsearch.easyes.service;

import com.y.gui.elasticsearch.easyes.dto.EasyESOrderDTO;
import com.y.gui.elasticsearch.easyes.param.EasyEsQueryParam;
import com.y.gui.vo.PageParentVo;

public interface EasyEsService {
    /**
     * 分页查询
     * @param param 分页请求参数
     * @return 查询的分页数据
     */
    PageParentVo<EasyESOrderDTO> queryPageList(EasyEsQueryParam param);
}
