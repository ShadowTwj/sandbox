package cn.tianwenjie.hotdeploy;

import java.util.Scanner;

/**
 * 模拟热加载，动态Agent启动类
 *
 * @author tianwj
 * @date 2022/10/11 14:07
 */
public class HotLoadingRun {
    /**
     * 1. run {@link #main(String[])}
     * 2. run {@link HotLoadingAttach#main(String[])}，触发热加载
     * 自动加载/Users/tianwj/workspace/other/sandbox/agent-run/src/main/resources/HotLoadingRun.java
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HotLoadingRun...");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = scanner.next();
            print(param);
        }
    }

    public static String print(String param) throws InterruptedException {
        System.out.println(param);
        Thread.sleep(500);
        return "print:" + param;
    }
}
