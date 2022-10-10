package cn.tianwenjie.base;

import java.lang.instrument.Instrumentation;

/**
 * agent基础功能Demo
 *
 * @author tianwj
 * @date 2022/10/8 20:14
 */
public class AgentDemo {

    /**
     * 静态agent，启动时加载agent
     *
     * @param args 启动agent时参数
     * @param inst Instrumentation API
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println("AgentDemo premain 1");
    }

    /**
     * 静态agent，启动时加载agent
     * JVM 会优先加载带Instrumentation签名的方法，加载成功忽略该方法，如果没有Instrumentation签名的方法，则加载该方法
     *
     * @param args 启动agent时参数
     */
    public static void premain(String args) {
        System.out.println("AgentDemo premain 2");
    }

    /**
     * 动态agent，运行时加载agent，通过attach方式
     */
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("AgentDemo agentmain 1");
    }

    /**
     * 动态agent，运行时加载agent，通过attach方式
     * JVM 会优先加载带Instrumentation签名的方法，加载成功忽略该方法，如果没有Instrumentation签名的方法，则加载该方法
     */
    public static void agentmain(String args) {
        System.out.println("AgentDemo agentmain 2");
    }
}
