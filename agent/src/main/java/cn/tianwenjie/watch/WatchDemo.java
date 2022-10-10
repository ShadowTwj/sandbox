package cn.tianwenjie.watch;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author tianwj
 * @date 2022/10/9 18:42
 */
public class WatchDemo {

    /**
     * 静态agent，增强类
     */
    private static final String PREMAIN_CLASS_PATH = "cn.tianwenjie.watch.WatchPremainRun";
    /**
     * 动态agent，增强类
     */
    private static final String AGENTMAIN_CLASS_PATH = "cn.tianwenjie.watch.WatchAgentmainRun";

    /**
     * 增强方法名
     */
    private static final String ENHANCE_METHOD_NAME = "print";

    public static void premain(String args, Instrumentation inst) {
        System.out.println("WatchDemo premain");

        inst.addTransformer(new WatchTransformer(PREMAIN_CLASS_PATH, ENHANCE_METHOD_NAME), true);
    }

    public static void agentmain(String args, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("WatchDemo agentmain");

        inst.addTransformer(new WatchTransformer(AGENTMAIN_CLASS_PATH, ENHANCE_METHOD_NAME), true);

        // 动态agent，所有类已加载完成，需要增强的类重新转换
        Class<?> agentmainClass = Class.forName(AGENTMAIN_CLASS_PATH);
        inst.retransformClasses(agentmainClass);
    }
}
