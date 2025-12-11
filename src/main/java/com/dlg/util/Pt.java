package com.dlg.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 轻量级打印，1方便后续升级，2减少代码，3降低耦合
 *
 * @author lingui
 * @Date 2024/7/4 9:43
 */
public class Pt {

    private static int index = 0;

    /**
     * 打印
     *
     * @param msg   消息
     * @param items 替换
     */
    public static void info(String msg, Object... items) {
        if (StringUtils.isBlank(msg)) {
            System.out.println();
            return;
        }
        for (Object item : items) {
            msg = msg.replaceFirst("\\{}", item.toString());
        }
        System.out.println(++index + " " + msg);
    }

    public static void error(String msg, Object... items) {
        if (StringUtils.isBlank(msg)) {
            System.err.println();
            return;
        }
        for (Object item : items) {
            msg = msg.replaceFirst("\\{}", item.toString());
        }
        System.err.println(msg);
    }

    public static void error(Throwable t) {
        error(t.getMessage());
    }

}
