package com.dlg;

import com.dlg.common.JConst;
import com.dlg.compant.ScheduleComp;
import com.dlg.controller.Webhook;
import com.dlg.util.MqUtil;
import com.dlg.util.Pt;
import com.dlg.util.ThreadPool;
import com.dlg.view.client.MainClient;

import javax.swing.*;

/**
 * 应用程序入口
 */
public class MacClientApplication {

    /**
     * 客户端对象
     */
    private static final MainClient CLIENT = new MainClient();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CLIENT::init);
        startBootTask();
        Pt.info("启动版本"+JConst.VERSION);
    }

    /**
     * 启动项
     */
    private static void startBootTask() {
        // 外部回调服务函数
        Webhook.getWebhook().startListen(19009);
        // 周期任务
        ThreadPool.submit(ScheduleComp.getScheduleComp());
        // MQ任务 
        MqUtil.start();
    }

}
