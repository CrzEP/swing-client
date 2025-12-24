package com.dlg.compant;

import com.dlg.handler.ExceptionHandler;
import com.dlg.util.MqUtil;
import com.dlg.util.Pt;
import lombok.Getter;

/**
 * 周期组件
 */
public class ScheduleComp implements Runnable{

    private Long index = 0L;

    @Getter
    private static ScheduleComp scheduleComp = new ScheduleComp();

    @Override
    public void run() {
        ExceptionHandler.addExHandler();
        Pt.info("周期线程启动");
        while(true){
            try {
                ++index;
                taskCall();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Pt.error(e);
            }
        }
    }

    private synchronized void taskCall() {
        // 调用吐丝函数
        MqUtil.pushToast("toast" +System.currentTimeMillis());
    }

}
