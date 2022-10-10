package cn.tianwenjie.watch;

import java.util.Scanner;

/**
 * @author tianwj
 * @date 2022/10/9 18:55
 */
public class WatchAgentmainRun {

    /**
     * 1. run {@link #main(String[])}
     * 2. run {@link WatchAttach#main(String[])}
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("WatchAgentmainRun...");
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
