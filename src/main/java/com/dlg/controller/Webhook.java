package com.dlg.controller;

import com.dlg.handler.ExceptionHandler;
import com.dlg.util.MqUtil;
import com.dlg.util.Pt;
import com.dlg.util.ThreadPool;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * API 钩子，API服务
 * 若需要多个服务，则需要改造
 */
public class Webhook implements Runnable {

    @Getter
    private static Webhook webhook = new Webhook();

    private int port = 19009;

    /**
     * 接收到消息
     *
     * @param message 消息
     */
    public void onMessage(String message) {
        System.out.println(message);
        MqUtil.pushToast(message);
    }

    @Override
    public void run() {
        ExceptionHandler.addExHandler();
        try (ServerSocket serv = new ServerSocket(port)) {
            System.out.printf("绑定监听端口: " + serv.getLocalPort());
            Socket socket;
            while (true) {
                // 接收连接，如果没有连接，accept() 方法会阻塞
                socket = serv.accept();
                InputStream inputStream = socket.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                while (inputStream.read() != -1) {
                    List<String> strings = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
                    for (String string : strings) {
                        stringBuilder.append(string);
                    }
                }
                onMessage(stringBuilder.toString());
            }
        } catch (IOException e) {
            System.err.println(e);
            Pt.error(e.getMessage());
        }
    }

    /**
     * 开始监听
     *
     * @param port 端口
     */
    public void startListen(int port) {
        this.port = port;
        ThreadPool.submit(this);
    }

}
