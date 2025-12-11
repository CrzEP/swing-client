package com.dlg.view.panel;

import com.dlg.view.GuiInit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;

public class LeftMenu extends JPanel implements GuiInit {

    @Override
    public void init(){
        this.setLayout(new GridLayout(16, 1));
        Dimension dimension = new Dimension(120,40);
        // 按钮
        LinkedHashMap<String,Button> buttonMap = new LinkedHashMap<>();
        buttonMap.put(BodyPanel.HOME,new Button("首页"));
        buttonMap.put(BodyPanel.LOCAL_FUNCTION,new Button("本地功能"));
        buttonMap.put(BodyPanel.FILE_PROCESS,new Button("文件处理"));

        // 循环设置
        for(String name : buttonMap.keySet()){
            Button button = buttonMap.get(name);
            button.setPreferredSize(dimension);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    BodyPanel.getInstance().showItem(name,name);
                }
            });
            this.add(button);
        }
        setBackground(Color.white);
    }

}
