package xin.altitude.cms.framework.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import xin.altitude.cms.common.util.StringUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 错误信息处理类。
 *
 * @author ucode
 */
public class ExceptionUtil {
    /**
     * 获取exception的详细错误信息。
     */
    public static String getExceptionMessage(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }
    
    public static String getRootErrorMessage(Exception e) {
        Throwable root = ExceptionUtils.getRootCause(e);
        root = (root == null ? e : root);
        if (root == null) {
            return "";
        }
        String msg = root.getMessage();
        if (msg == null) {
            return "null";
        }
        return StringUtil.defaultString(msg);
    }
}
