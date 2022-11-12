package cn.tianwenjie.hotdeploy.common;

import java.io.IOException;

/**
 * @author tianwj
 * @date 2022/11/12 16:56
 */
public class DynamicCompilerTest {

    public static void main(String[] args) throws IOException {
        DynamicCompiler dynamicCompiler = new DynamicCompiler();
        byte[] byteCode = dynamicCompiler.compiler(
                "/Users/tianwj/workspace/other/sandbox/agent-run/src/main/resources/HotLoadingRun.java");
        System.out.println(new String(byteCode));
    }
}
