package xin.altitude.cms.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import xin.altitude.cms.common.entity.AjaxResult;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 响应结果工具类
 *
 * @author 赛泰先生
 */
public class ResponseUtils {
    private final static Logger log = LoggerFactory.getLogger(ResponseUtils.class);


    /**
     * Response输出Json格式
     *
     * @param result 返回数据
     */
    public static void responseJson(AjaxResult result) {
        responseJson(ServletUtils.getResponse(), result);
    }

    /**
     * Response输出Json格式
     *
     * @param response 相应
     * @param result   返回数据
     */
    public static void responseJson(ServletResponse response, AjaxResult result) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            String value = JacksonUtils.writeValueAsString(result);
            StreamUtils.copy(value, StandardCharsets.UTF_8, response.getOutputStream());
        } catch (IOException e) {
            log.error("Response输出Json异常：" + e);
        }
    }

}
