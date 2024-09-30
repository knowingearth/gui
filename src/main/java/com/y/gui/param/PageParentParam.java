package com.y.gui.param;

import com.y.gui.common.annotations.validation.In;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PageParentParam {
    @Schema(
            title = "pageNum",
            description = "页码",
            format = "int",
            example = "1"
    )
    @Max(value = Integer.MAX_VALUE,message = "pageNum过大")
    @Min(value = 1,message = "pageNum不能小于1")
    protected Integer pageNum;

    @Schema(
            title = "pageSize",
            description = "页大小",
            format = "int",
            example = "20"
    )
    @Max(value = 500,message = "pageSize不能大于500")
    @Min(value = 1,message = "pageSize不能小于1")
    protected Integer pageSize;

    @Schema(
            title = "sortType",
            description = "升降序(空采用接口默认排序，asc/desc)",
            format = "string",
            example = "desc"
    )
    @In(value = {"asc","desc"}, message = "排序类型必须指定为asc或desc")
    protected String sortType;
}
