package xin.altitude.cms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Consumer;

public abstract class ExceptionUtils {
    private final static Logger log = LoggerFactory.getLogger(ExceptionUtils.class);

    public static <T> void forceRun(T t, Consumer<T> consumer) {
        try {
            consumer.accept(t);
        } catch (Exception e) {
            log.warn("忽略异常，仍然保持执行：{}", e.getMessage());
        }
    }
}
