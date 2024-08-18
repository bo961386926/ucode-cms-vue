package xin.altitude.cms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.common.constant.PatternConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class InvokeMethodUtils {
    private final static Logger log = LoggerFactory.getLogger(InvokeMethodUtils.class);

    public static List<String> invokePath(StackTraceElement[] stackTraceElements, Predicate<String> predicate) {
        List<String> ss = new ArrayList<>();
        for (StackTraceElement caller : stackTraceElements) {
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            int lineNumber = caller.getLineNumber();
            if (lineNumber > 0) {
                if (predicate == null || predicate.test(className)) {
                    String newClassName = className.replaceAll(PatternConstants.P1, "");
                    ss.add(String.format("%s#%s:%s", newClassName, methodName, lineNumber));
                }
            }
        }
        return ss;
    }

    /**
     * 方法调用路径
     */
    public static void invokePath() {
        String allowPackagePrefix = System.getProperty("allowPackagePrefix");
        Predicate<String> pred = null;
        if (allowPackagePrefix != null) {
            pred = allowPackagePrefix::startsWith;
        }
        List<String> paths = invokePath(Thread.currentThread().getStackTrace(), pred);
        if (!paths.isEmpty()) {
            log.warn("调用路径\n{}", String.join("\n", paths));
        }
    }
}
