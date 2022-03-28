package xin.ucode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.RedisUtils;

/**
 * 启动程序
 *
 * @author ucode
 */
@SpringBootApplication
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
        for (long i = 1; i <= 10; i++) {
            /* 初始化BitMap */
            RedisUtils.setBit(Constants.BITMAP_KEY, i);
        }
    }
}
