package com.y.gui.common.bases;

import com.fasterxml.jackson.annotation.JsonView;
import com.y.gui.common.enums.ResultEnum;
import com.y.gui.view.GuiView;
import lombok.Data;

@Data
public class ResultEntity<T> {
    /**
     * 响应code码
     */
    @JsonView(GuiView.class)
    private Integer code;

    /**
     * 响应描述
     */
    @JsonView(GuiView.class)
    private String message;

    /**
     * 响应数据
     */
    @JsonView(GuiView.class)
    private T data;

    private ResultEntity() {
    }

    private ResultEntity(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultEntity(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultEntity<T> getInstance(ResultEnum resultEnum) {
        return new ResultEntity<T>(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static <T> ResultEntity<T> getInstance(ResultEnum resultEnum, T data) {
        return new ResultEntity<T>(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static <T> ResultEntity<T> getSuccessInstance() {
        return getInstance(ResultEnum.SUCCESS);
    }

    public static <T> ResultEntity<T> getSuccessInstance(T data) {
        return getInstance(ResultEnum.SUCCESS, data);
    }
}
