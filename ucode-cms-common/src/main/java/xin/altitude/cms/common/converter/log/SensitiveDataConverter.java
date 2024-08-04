package xin.altitude.cms.common.converter.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class SensitiveDataConverter extends ClassicConverter {
    private static final String PHONE_PATTERN = "(1\\d{2})\\d{4}(\\d{4})";

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        // 使用正则表达式匹配并脱敏手机号
        return message.replaceAll(PHONE_PATTERN, "$1****$2");
    }
}
