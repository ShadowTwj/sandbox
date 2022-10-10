package cn.tianwenjie.base;

import cn.tianwenjie.AttachUtil;

/**
 * @author tianwj
 * @date 2022/10/9 15:50
 */
public class AttachRun {

    private static final String AGENT_PATH = "/Users/tianwj/workspace/other/sandbox/agent/target/agent-jar-with-dependencies.jar";

    public static void main(String[] args) throws Exception {
        AttachUtil.attach(AGENT_PATH, "AgentmainRun");
    }
}
