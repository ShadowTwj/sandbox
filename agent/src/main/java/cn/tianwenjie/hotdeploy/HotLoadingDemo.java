package cn.tianwenjie.hotdeploy;

import cn.tianwenjie.hotdeploy.common.DynamicCompiler;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * 热加载Agent
 *
 * @author tianwj
 * @date 2022/10/11 11:30
 */
public class HotLoadingDemo {
    /**
     * 热部署源类路径
     */
    private static final String SOURCE_CLASS_PATH = "cn.tianwenjie.hotdeploy.HotLoadingRun";

    /**
     * 热部署替换目标Java文件路径
     */
    private static final String TARGET_FILE_PATH = "/Users/tianwj/workspace/other/sandbox/agent-run/src/main/resources/HotLoadingRun.java";

    public static void premain(String args, Instrumentation inst) {
        System.out.println("HotLoadingDemo premain");
    }

    public static void agentmain(String args, Instrumentation inst) throws Exception {
        System.out.println("HotLoadingDemo agentmain");

        Class<?>[] allLoadedClasses = inst.getAllLoadedClasses();

        for (Class<?> loadedClass : allLoadedClasses) {
            if (SOURCE_CLASS_PATH.equals(loadedClass.getName())) {
                // 自定义编译器，将替换的代码编译成字节码
                DynamicCompiler dynamicCompiler = new DynamicCompiler();
                byte[] byteCode = dynamicCompiler.compiler(TARGET_FILE_PATH);

                ClassDefinition classDefinition = new ClassDefinition(loadedClass, byteCode);
                inst.redefineClasses(classDefinition);
            }
        }
    }
}
