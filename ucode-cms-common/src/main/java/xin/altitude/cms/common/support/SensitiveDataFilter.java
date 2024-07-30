package xin.altitude.cms.common.support;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class SensitiveDataFilter extends AbstractMatcherFilter<LoggingEvent> {

    @Override
    public FilterReply decide(LoggingEvent event) {
        String message = event.getFormattedMessage();

        // 1. 匹配手机号码并脱敏
        String filteredMessage = message.replaceAll("(^1[3-9]\\d{3})\\d{4}(\\d{4}$)", "$1****$2");

        event.setMessage(filteredMessage);
        return FilterReply.NEUTRAL;
    }
}
