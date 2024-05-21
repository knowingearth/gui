package com.y.gui.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.y.gui.common.bases.ResultEntity;
import com.y.gui.dto.AreaDTO;
import com.y.gui.service.AreaService;
import com.y.gui.view.GuiAreaView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全国区划
 */
@Slf4j
@Tag(name = "AreaControllerAPI", description = "全国区划")
@RestController
@RequestMapping("area")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 新增
     * @param areaDTO
     * @return
     */
    @RequestMapping("add")
    public ResultEntity<Long> addArea(@RequestBody AreaDTO areaDTO) {
        Long id = areaService.addArea(areaDTO);
        return ResultEntity.getSuccessInstance(id);
    }




    @JsonView(GuiAreaView.Basic.class)
    @RequestMapping("getArea/{areaId}")
    public ResultEntity<AreaDTO> getArea(@PathVariable("areaId") Long areaId) {

        return ResultEntity.getSuccessInstance(areaService.getAreaById(areaId));
    }

    @JsonView(GuiAreaView.Simple.class)
    @RequestMapping("getAreaName/{areaId}")
    public ResultEntity<AreaDTO> getAreaName(@PathVariable("areaId") Long areaId) {
        return ResultEntity.getSuccessInstance(areaService.getAreaById(areaId));
    }
}
