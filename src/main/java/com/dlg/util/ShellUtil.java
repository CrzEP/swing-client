package com.dlg.util;

import com.dlg.exception.BuinssException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ShellUtil {

    public static String cmd(String command) {
        StringBuilder sb = new StringBuilder();
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            if (!p.waitFor(3000, TimeUnit.MILLISECONDS)) {
                return String.format("Run Shell Command Error: %s", command);
            } else {
                // 读取数据
                BufferedInputStream in = new BufferedInputStream(p.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                in.close();
            }
        } catch (Exception e){
            Pt.error("exec cmd io exception...");
            throw new BuinssException(command +" 执行失败: "+e.getMessage());
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(cmd("dlg toast hinow"));
    }

}
