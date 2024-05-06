package com.y.gui.view;

/**
 * 全国区划视图规则
 */
public interface GuiAreaView {
    /**
     * 基本信息
     */
    public interface Basic extends GuiView {}

    /**
     * 简要信息
     */
    public interface Simple extends GuiView {}

    /**
     * 全部信息
     */
    public interface Full extends GuiView {}
}
