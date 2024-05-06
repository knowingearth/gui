package com.y.gui.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.y.gui.view.GuiAreaView;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AreaDTO {
    @JsonView(GuiAreaView.Full.class)
    private Long id;

    @JsonView({GuiAreaView.Simple.class, GuiAreaView.Basic.class})
    private Long code;

    @JsonView(GuiAreaView.Basic.class)
    private Long parentCode;

    @JsonView({GuiAreaView.Simple.class, GuiAreaView.Basic.class})
    private String name;

    @JsonView({GuiAreaView.Simple.class, GuiAreaView.Basic.class})
    private String shortName;

    @JsonView(GuiAreaView.Full.class)
    private Float longitude;

    @JsonView(GuiAreaView.Full.class)
    private Float latitude;

    @JsonView({GuiAreaView.Basic.class, GuiAreaView.Full.class})
    private Integer level;

    @JsonView(GuiAreaView.Basic.class)
    private Integer urCode;

    @JsonView(GuiAreaView.Full.class)
    private Integer sort;

    @JsonView(GuiAreaView.Full.class)
    private Integer status;

    @JsonView(GuiAreaView.Full.class)
    private LocalDateTime createTime;

    @JsonView(GuiAreaView.Full.class)
    private LocalDateTime updateTime;
}
