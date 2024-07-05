package com.y.gui.dao;

import com.y.gui.po.GuiArea;
import com.y.gui.po.GuiAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GuiAreaMapper {
    long countByExample(GuiAreaExample example);

    int deleteByExample(GuiAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GuiArea record);

    int insertSelective(GuiArea record);

    List<GuiArea> selectByExample(GuiAreaExample example);

    GuiArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GuiArea record, @Param("example") GuiAreaExample example);

    int updateByExample(@Param("record") GuiArea record, @Param("example") GuiAreaExample example);

    int updateByPrimaryKeySelective(GuiArea record);

    int updateByPrimaryKey(GuiArea record);
}