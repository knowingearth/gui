package com.y.gui.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageParentVo<T> {
    @Schema(
            title = "pageNum",
            description = "当前页码",
            format = "int",
            example = "1"
    )
    private long pageNum;


    @Schema(
            title = "pageSize",
            description = "每页大小",
            format = "int",
            example = "20"
    )
    private Integer pageSize;


    @Schema(
            title = "total",
            description = "总条目数",
            format = "int",
            example = "200"
    )
    private Long total;

    @Schema(
            title = "list",
            description = "数据列表",
            format = "array"
    )
    private List<T> list;
}
