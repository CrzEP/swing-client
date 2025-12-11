package com.dlg.view.setting;

import com.dlg.view.GuiInit;

import javax.swing.*;
import java.awt.*;

public class GobleSetting implements GuiInit {

    PropUtil propUtil = new PropUtil();

    @Override
    public void init() {
        // 读取
        propUtil.readConfig();
        fontSetting();
    }

    private void fontSetting() {
        // 设置全局字体
        Font font = new Font("Arial", Font.PLAIN, propUtil.getInt(PropUtil.fontSize));
        // 设置字体为 Arial，普通样式，大小为 16
        UIManager.put("Button.font", font);
        // 设置按钮的字体
        UIManager.put("Label.font", font);
        // 设置标签的字体
        UIManager.put("TextField.font", font);
        // 设置文本框的字体
        UIManager.put("TextArea.font", font);
        // 设置文本区的字体
        UIManager.put("Menu.font", font);
        // 设置菜单的字体
    }
}
