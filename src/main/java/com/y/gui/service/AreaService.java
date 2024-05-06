package com.y.gui.service;

import com.y.gui.dto.AreaDTO;

/**
 * 全国区划
 */
public interface AreaService {
    /**
     * 新增区划
     * @param areaDTO
     * @return
     */
    Long addArea(AreaDTO areaDTO);

    /**
     * 根据ID获取详情
     * @param areaId
     * @return
     */
    AreaDTO getAreaById(Long areaId);
}
