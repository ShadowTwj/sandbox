package cn.tianwenjie.base;

import java.util.Scanner;

/**
 * @author tianwj
 * @date 2022/10/9 11:04
 */
public class AgentmainRun {

    /**
     * 1. run {@link #main(String[])}
     * 2. run {@link AttachRun#main(String[])}
     */
    public static void main(String[] args) {
        System.out.println("AgentmainRun...");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = scanner.next();
            print(param);
        }
    }

    public static void print(String param) {
        System.out.println(param);
    }
}