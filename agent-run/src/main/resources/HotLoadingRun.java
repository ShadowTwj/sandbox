package cn.tianwenjie.hotdeploy;

import java.util.Scanner;

/**
 * 模拟热加载
 * 替换目标类型
 *
 * @author tianwj
 * @date 2022/10/11 14:07
 */
public class HotLoadingRun {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("HotLoadingRun...");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String param = scanner.next();
            print(param);
        }
    }

    public static String print(String param) throws InterruptedException {
        System.out.println("热部署成功！！！");
        System.out.println(param);
        Thread.sleep(500);
        return "print:" + param;
    }
}
