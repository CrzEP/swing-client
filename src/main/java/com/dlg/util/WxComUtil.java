package com.dlg.util;

/**
 * 企业微信工具
 */
public class WxComUtil {

    /**
     * 向企业微信发送消息
     *
     * @param msg 消息内容
     */
    public void send(String msg) {
        String cmd = "dlg toast ";
        ShellUtil.cmd(cmd + msg);
    }

}
