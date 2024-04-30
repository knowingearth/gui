package com.y.gui.dao;

import com.y.gui.po.GuiArea;

public interface GuiAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GuiArea record);

    int insertSelective(GuiArea record);

    GuiArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GuiArea record);

    int updateByPrimaryKey(GuiArea record);
}