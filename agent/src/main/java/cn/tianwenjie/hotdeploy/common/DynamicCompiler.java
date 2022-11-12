package cn.tianwenjie.hotdeploy.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * 自定义编译器
 *
 * @author tianwj
 * @date 2022/11/11 17:29
 */
public class DynamicCompiler {
    private final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

    private final StandardJavaFileManager standardFileManager;

    public DynamicCompiler() {
        standardFileManager = javaCompiler.getStandardFileManager(null, null, StandardCharsets.UTF_8);
    }

    /**
     * compile
     *
     * @param classPath Java文件路径
     * @return 字节码byte数组
     */
    public byte[] compiler(String classPath) throws IOException {
        // 构建输入对象
        String javaContent = readContext(classPath);
        String className = DevUtil.getClassName(javaContent);
        JavaCodeFileObject javaCodeFileObject = new JavaCodeFileObject(javaContent, className);

        // 构建输出对象
        DynamicJavaFileManager dynamicJavaFileManager = new DynamicJavaFileManager(standardFileManager);

        // compile
        JavaCompiler.CompilationTask task =
                javaCompiler.getTask(null, dynamicJavaFileManager, System.out::println, null, null, Collections.singletonList(javaCodeFileObject));
        Boolean call = task.call();

        if (Boolean.FALSE.equals(call)) {
            // 编译失败处理
            return null;
        } else {
            ByteCodeFileObject byteCodeFileObject = dynamicJavaFileManager.getByteCodeFileObject(className);
            return byteCodeFileObject.getByteArray();
        }
    }

    private static String readContext(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes);
    }

    /**
     * 自定义Java输入对象
     */
    static class JavaCodeFileObject extends SimpleJavaFileObject {
        /**
         * 代码内容
         */
        private final String content;

        protected JavaCodeFileObject(String content, String className) {
            super(URI.create("string:///" + className.replace(".", "/") + ".java"), Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }


    /**
     * 自定义JavaFileManager，封装输出对象
     */
    static class DynamicJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {

        /**
         * Creates a new instance of ForwardingJavaFileManager.
         *
         * @param fileManager delegate to this file manager
         */
        protected DynamicJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        /**
         * 类名对应字节码对象
         */
        private final Map<String, ByteCodeFileObject> byteCodeFileObjectMap = new HashMap<>();

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling)
                throws IOException {
            if (JavaFileObject.Kind.CLASS == kind) {
                ByteCodeFileObject byteArrayJavaFileObject = new ByteCodeFileObject(className);
                byteCodeFileObjectMap.put(byteArrayJavaFileObject.getClassName(), byteArrayJavaFileObject);
                return byteArrayJavaFileObject;
            } else {
                return super.getJavaFileForOutput(location, className, kind, sibling);
            }
        }

        public ByteCodeFileObject getByteCodeFileObject(String className) {
            return byteCodeFileObjectMap.get(className);
        }
    }


    /**
     * 自定义字节码输出对象
     */
    static class ByteCodeFileObject extends SimpleJavaFileObject {
        private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        protected ByteCodeFileObject(String className) {
            super(URI.create("bytes:///" + className.replace(".", "/") + ".class"), Kind.CLASS);
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return this.byteArrayOutputStream;
        }

        public byte[] getByteArray() {
            return this.byteArrayOutputStream.toByteArray();
        }

        /**
         * 构造com.xxx.xxx.Xxx格式类名
         */
        public String getClassName() {
            String className = getName();
            className = className.replace("/", ".");
            className = className.substring(1, className.lastIndexOf(".class"));
            return className;
        }
    }
}
