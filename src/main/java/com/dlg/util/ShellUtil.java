package com.dlg.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ShellUtil {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.print("input:");
//            String line = sc.nextLine();
//            String shell = winShell(line);
//            System.out.println(shell);
//        }
        winShell("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
    }

    public static String winShell(String command) {
        StringBuilder sb = new StringBuilder();
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            System.out.println("wait exec...");
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
        } catch (IOException e){
            Pt.error("exec cmd io exception...");
        } catch (Exception e) {
            Pt.error("exec cmd:{} fail",command);
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return sb.toString();
    }

}
