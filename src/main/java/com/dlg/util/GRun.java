package com.dlg.util;


import javax.swing.*;

/**
 * 多线程操作ui
 */
public class GRun {

    public interface GuiRunner{

        void run() ;

    }

    /**
     * 更新UI
     * @param runnable 需要操作的内容
     */
    public static void update(GuiRunner runnable) {
        SwingUtilities.invokeLater(() -> {
            try {
                runnable.run();
            }catch (Exception e){
                Pt.error(e.getMessage());
            }
        });
    }

}
