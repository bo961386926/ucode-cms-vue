package xin.altitude.cms.take.time.uril;

/**
 * @author 塞泰先生
 * @since 2023/10/10 17:55
 **/
public class DurationUtils {
    /**
     * 持续时间语意丰富展示
     *
     * @param duration 时间戳（毫秒）
     */
    public static String calTime(long duration) {
        if (0 < duration && duration < 100) {
            return String.format("%s毫秒", duration);
        } else if (duration > 100 && duration < 60000) {
            return String.format("%s秒", duration * 1.0 / 1000);
        } else {
            // 分钟
            double m = duration * 1.0 / (60 * 1000);
            // 秒
            double sec = (duration - (m * 60 * 1000)) / 1000;
            return String.format("%s分钟%s秒", m, sec);
        }
    }
}
