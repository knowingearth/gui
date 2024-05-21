package com.y.gui.service.impl;

import com.alibaba.fastjson.JSON;
import com.y.gui.common.annotations.CLog;
import com.y.gui.dao.GuiAreaMapper;
import com.y.gui.dto.AreaDTO;
import com.y.gui.po.GuiArea;
import com.y.gui.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 全国区划
 */
@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private GuiAreaMapper areaMapper;

    /**
     * 新增区划
     * @param areaDTO
     * @return
     */
    @CLog
    @Override
    public Long addArea(AreaDTO areaDTO) {
        GuiArea area = new GuiArea();
        BeanUtils.copyProperties(areaDTO, area);
        area.setCreateTime(LocalDateTime.now());
        int result = areaMapper.insertSelective(area);
        log.info("AreaServiceImpl.addArea, result:{}, area:{}", result, JSON.toJSONString(area));
        return area.getId();
    }

    /**
     * 根据ID获取详情
     * @param areaId
     * @return
     */
    @CLog
    @Override
    public AreaDTO getAreaById(Long areaId) {
        GuiArea area = areaMapper.selectByPrimaryKey(areaId);
        if (null == area) {
            return null;
        }
        AreaDTO areaDTO = new AreaDTO();
        BeanUtils.copyProperties(area, areaDTO);
        return areaDTO;
    }
}
