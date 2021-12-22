package xin.altitude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.utils.spring.SpringUtils;

/**
 * 启动程序
 *
 * @author ucode
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CmsApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CmsApplication.class, args);
        System.out.println(SpringUtils.getBean(CmsConfig.class));
        System.out.println("项目启动成功");
    }
}
