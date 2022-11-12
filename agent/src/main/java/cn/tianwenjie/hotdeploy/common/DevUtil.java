package cn.tianwenjie.hotdeploy.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tianwj
 * @date 2022/11/12 17:12
 */
public class DevUtil {
    private static String PID = "-1";

    private static final Pattern packagePattern = Pattern.compile("package\\s*(\\S*);");

    private static final Pattern classPattern = Pattern.compile("public\\s*class\\s*([a-zA-Z0-9_$]*)");

    /**
     * 根据Java代码获取类名
     * com.xxx.xxx.Xxx格式
     */
    public static String getClassName(String content) {
        Matcher packageMatcher = packagePattern.matcher(content);
        Matcher classMatcher = classPattern.matcher(content);
        if (!(packageMatcher.find() && classMatcher.find())) {
            throw new IllegalStateException("This is an illegal class");
        }
        String className = packageMatcher.group(1) + "." + classMatcher.group(1);
        return className;
    }
}
