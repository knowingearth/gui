package com.y.gui.dao;

import com.y.gui.po.GuiArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuiAreaExMapper {

    /**
     * 插入或更新数据
     */
    int insertOrUpdateSelective(GuiArea record);

    /**
     * 批量插入或更新数据-以下注意事项
     * 如果存在po属性值为null并且插入操作违反主键约束导致更新row数据的时候：
     *     如果字段可空，则库里字段值将设置为null；
     *     如果字段必填，将抛异常！
     *       org.springframework.dao.DataIntegrityViolationException
     *       java.sql.SQLIntegrityConstraintViolationException: Column '***' cannot be null
     *
     * 返回int计算规则：int = (入参list.size() + 更新条数)
     */
    int batchInsertOrUpdateSelective(@Param("records") List<GuiArea> records);

}