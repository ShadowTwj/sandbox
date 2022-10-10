package cn.tianwenjie;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.util.List;

/**
 * attach工具类
 *
 * @author tianwj
 * @date 2022/10/10 17:47
 */
public class AttachUtil {

    public static void attach(String agentPath, String runDisplayName) throws Exception {
        // 获取所有VM实例
        List<VirtualMachineDescriptor> list = VirtualMachine.list();

        for (VirtualMachineDescriptor descriptor : list) {
            System.out.println("pid:" + descriptor.id() + " displayName:" + descriptor.displayName());

            if (descriptor.displayName().endsWith(runDisplayName)) {
                // attach对应VM
                VirtualMachine vm = VirtualMachine.attach(descriptor);
                // 加载目标Agent
                vm.loadAgent(agentPath);
                vm.detach();
            }
        }
    }
}
