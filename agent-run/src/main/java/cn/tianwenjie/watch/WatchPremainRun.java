package cn.tianwenjie.watch;

import java.util.Scanner;

/**
 * 模拟watch功能，静态agent启动类
 *
 * @author tianwj
 * @date 2022/10/9 18:55
 */
public class WatchPremainRun {

    /**
     * add VM options:
     * -javaagent:/java-agent路径/agent-jar-with-dependencies.jar
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("WatchPremainRun...");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = scanner.next();
            print(param);
        }
    }

    /**
     * 测试耗时方法
     */
    public static String print(String param) throws InterruptedException {
        System.out.println(param);
        Thread.sleep(500);
        return "print:" + param;
    }
}
