package cn.tianwenjie.watch;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * 模拟watch功能类转换器
 *
 * @author tianwj
 * @date 2022/10/9 18:49
 */
public class WatchTransformer implements ClassFileTransformer {

    /**
     * 需要增强类名
     */
    private final String enhanceClassName;

    /**
     * 需要增强方法名
     */
    private final String enhanceMethodName;

    public WatchTransformer(String targetClassPath, String enhanceMethodName) {
        this.enhanceClassName = targetClassPath.replace(".", "/");
        this.enhanceMethodName = enhanceMethodName;
    }

    @Override
    public byte[] transform(
            ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {

        System.out.println("正在加载类：" + className);

        // 非需要增强类时直接返回
        if (!enhanceClassName.equals(className)) {
            return classfileBuffer;
        }

        try {
            // 使用javassist修改字节码
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
            CtMethod method = ctClass.getDeclaredMethod(enhanceMethodName);

            method.addLocalVariable("startTime", CtClass.longType);
            method.insertBefore("startTime = System.currentTimeMillis();");

            method.addLocalVariable("endTime", CtClass.longType);
            method.insertAfter("endTime = System.currentTimeMillis();");

            // 打印方法耗时
            method.insertAfter("System.out.println(\"method cost:\" + (endTime - startTime));");

            // 返回增强后的字节码
            return ctClass.toBytecode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
