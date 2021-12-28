package xin.altitude.cms.common.util.ip;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.StringUtils;
import xin.altitude.cms.common.util.http.HttpUtils;
import xin.altitude.cms.common.util.spring.SpringUtils;

/**
 * 获取地址类
 *
 * @author ucode
 */
public class AddressUtils {
    // IP地址查询
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
    // 未知地址
    public static final String UNKNOWN = "XX XX";
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);
    
    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (SpringUtils.getBean(CmsConfig.class).getCms().isAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }
}